package com.uifuture.ssm.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.base.page.Page;
import com.uifuture.ssm.bo.UsersPayQueryBo;
import com.uifuture.ssm.common.RedisConstants;
import com.uifuture.ssm.config.SysConfig;
import com.uifuture.ssm.convert.UsersPayConvert;
import com.uifuture.ssm.dto.PageDTO;
import com.uifuture.ssm.dto.UsersPayDTO;
import com.uifuture.ssm.email.EmailConfig;
import com.uifuture.ssm.email.SendPayCheckEmail;
import com.uifuture.ssm.email.impl.SendPayCheckEmailCallable;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.entity.UsersPayEntity;
import com.uifuture.ssm.enums.PayTypeEnNameEnum;
import com.uifuture.ssm.enums.UsersPayStateEnum;
import com.uifuture.ssm.redis.RedisClient;
import com.uifuture.ssm.req.UsersPayReq;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.UsersPayService;
import com.uifuture.ssm.service.UsersService;
import com.uifuture.ssm.util.BeanConvertUtils;
import com.uifuture.ssm.util.PasswordUtils;
import com.uifuture.ssm.util.ValidateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户支付信息详情表 前端控制器
 * </p>
 *
 * @author chenhx
 * @since 2019-11-06
 */
@RestController
@RequestMapping("/users-pay-entity")
@Slf4j
public class UsersPayController extends BaseController {

    /**
     * 审核接口
     */
    private static final String passUrl = "http://uifuture.com/users-pay-entity/payPass";
    /**
     * 最小支付金额
     */
    private static final BigDecimal MIN_PAY = new BigDecimal("0.01");
    /**
     * 最大支付金额
     */
    private static final BigDecimal MAX_PAY = new BigDecimal("1000");

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private SysConfig sysConfig;
    /**
     * 你的应用ID
     */
    private static final String appId = "2017022505878787";

    /**
     * 回调通知接口链接
     */
    private static final String notifyUrl = "http://uifuture.com/users-pay-entity/alipay/notify";
    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private UsersPayService usersPayService;
    @Autowired
    private UsersService usersService;

    /**
     * 生成支付二维码
     *
     * @return
     */
    @RequestMapping(value = "/alipay/createQrCode", method = RequestMethod.GET)
    public ResultModel alipayCreateQrCode(UsersPayReq usersPayReq, HttpServletRequest request) throws AlipayApiException {
//参数校验
        ValidateUtils.validate(usersPayReq);
//获取登录的用户
        UsersEntity usersEntity = getLoginInfo(request, true);
        if (usersPayReq.getMoney().compareTo(MIN_PAY) < 0 || usersPayReq.getMoney().compareTo(MAX_PAY) > 0) {
            return ResultModel.errorNoData("请填写正确的金额，当面付单笔金额不得大于1000");
        }
        String email = usersPayReq.getEmail();
        if (StringUtils.isEmpty(email)) {
            email = usersEntity.getEmail();
            if (StringUtils.isEmpty(email)) {
                return ResultModel.errorNoData("请填写正确的邮箱，或者使用账号绑定邮箱，以便接收通知邮件");
            }
        }

//防止频繁提交
        String key = RedisConstants.getAlipayCreateQrCode(usersEntity.getUsername());
        String isOne = redisClient.get(key).getObject(String.class);
        if (StringUtils.isNotEmpty(isOne)) {
//获取过期时间
            Long expire = redisClient.getRedisTemplate().getExpire(key, TimeUnit.SECONDS);
            return ResultModel.errorNoData("提交过于频繁请" + expire + "秒后再试");
        }
//添加支付信息
        UsersPayEntity usersPayEntity = UsersPayConvert.INSTANCE.usersPayReqToUsersPayEntity(usersPayReq);
        usersPayEntity.setEmail(email);
        usersPayEntity.setPayTypeEnName(PayTypeEnNameEnum.ALI_APY.getValue());
        String orderToken = PasswordUtils.getToken();
        usersPayEntity.setOrderNumber(orderToken);
        usersPayService.save(usersPayEntity);

//记录缓存
        redisClient.set(key, "1", 30);

        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",
                appId, sysConfig.getAliPayPrivateKey(), "json", "GBK", sysConfig.getAliPayPublicKey(), "RSA");
        AlipayTradePrecreateRequest r = new AlipayTradePrecreateRequest();
        r.setBizContent("{" +
                "\"out_trade_no\":\"" + usersPayEntity.getOrderNumber() + "\"," +
                "\"total_amount\":" + usersPayEntity.getMoney() + "," +
                "\"subject\":\"充值U币\"" +
                "  }");

        // 设置通知回调链接
        r.setNotifyUrl(notifyUrl);
        AlipayTradePrecreateResponse response = alipayClient.execute(r);
        if (!response.isSuccess()) {
            return ResultModel.errorNoData("调用支付宝接口生成二维码失败，请联系客服");
        }

        Map<String, Object> result = new HashMap<>(16);
        result.put("order_number", usersPayEntity.getOrderNumber());
        result.put("qr_code", response.getQrCode());
        return ResultModel.success(result);
    }


    /**
     * 支付二维码通知接口
     *
     * @return
     */
    @RequestMapping(value = "/alipay/notify", method = RequestMethod.POST)
    public ResultModel alipayNotify(@RequestParam(required = false, name = "out_trade_no") String outTradeNo,
                                    @RequestParam(required = false, name = "trade_status") String tradeStatus) {
        if ("TRADE_SUCCESS".equals(tradeStatus)) {
            sendActiveEmail(outTradeNo);
        }
        return ResultModel.success("success");
    }

    /**
     * 异步发送通知邮件
     *
     * @param outTradeNo
     */
    private void sendActiveEmail(String outTradeNo) {
        if (StringUtils.isEmpty(outTradeNo)) {
            return;
        }
        UsersPayEntity usersPayEntity = usersPayService.getByOrderNumber(outTradeNo);
        if (usersPayEntity == null) {
            return;
        }
        if (!UsersPayStateEnum.TO_BE_AUDITED.getValue().equals(usersPayEntity.getState())) {
            log.warn("[UsersPayController->sendActiveEmail]接收的订单支付信息状态不对,订单号={},数据库订单信息={}", outTradeNo, JSON.toJSONString(usersPayEntity));
            return;
        }
        //通过token控制，1天内有效
        String token = PasswordUtils.getSalt();
        //放入Redis
        redisClient.set(token, "1", RedisConstants.REG_MAX_TIME_1_DAY);
        //查询用户信息
        UsersEntity usersEntity = usersService.getById(usersPayEntity.getUsersId());
        if (usersEntity == null) {
            log.warn("[UsersPayController->sendActiveEmail]非法用户进行支付,订单号={},数据库订单信息={},usersEntity={}", outTradeNo, JSON.toJSONString(usersPayEntity), JSON.toJSONString(usersEntity));
            return;
        }
        //发送邮件，带上审核的链接即可，拼接上订单号与token
        SendPayCheckEmail sendPayCheckEmail = new SendPayCheckEmail() {
            @Override
            public String getEmail() {
                //管理员邮箱，进行审核支付订单
                return "code@uifuture.com";
            }

            @Override
            public String getUrl() {
                return passUrl + "?orderNumber=" + outTradeNo + "&token=" + token;
            }

            @Override
            public String getUserInfo() {
                return JSON.toJSONString(usersEntity);
            }

            @Override
            public String getPay() {
                return JSON.toJSONString(usersPayEntity);
            }
        };
        SendPayCheckEmailCallable sendEmailCallable = new SendPayCheckEmailCallable(emailConfig, sendPayCheckEmail);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(sendEmailCallable);
    }

    /**
     * 订单支付审核通过
     *
     * @return
     */
    @RequestMapping(value = "/payPass", method = RequestMethod.POST)
    public ResultModel pass(String orderNumber, String token) {
        if (StringUtils.isEmpty(orderNumber)) {
            return ResultModel.errorNoData("参数错误");
        }
        if (StringUtils.isEmpty(token)) {
            return ResultModel.errorNoData("参数错误");
        }
        //校验token未超时
        String value = redisClient.get(token).getString();
        if (StringUtils.isEmpty(value)) {
            return ResultModel.errorNoData("该订单已经无法再进行审核");
        }

        UsersPayEntity usersPayEntity = usersPayService.getByOrderNumber(orderNumber);
        if (usersPayEntity == null) {
            return ResultModel.errorNoData("参数错误");
        }
        if (!UsersPayStateEnum.TO_BE_AUDITED.getValue().equals(usersPayEntity.getState())) {
            return ResultModel.errorNoData("订单状态错误");
        }
        //用户增加记录和UB
        usersService.addUB(usersPayEntity);
        return ResultModel.success("success");
    }


    /**
     * 根据订单号获取支付信息
     *
     * @return
     */
    @RequestMapping(value = "/getPayInfo", method = RequestMethod.GET)
    public ResultModel getPayInfo(String orderNumber) {
        if (StringUtils.isEmpty(orderNumber)) {
            return ResultModel.errorNoData("参数错误");
        }
        UsersPayEntity usersPayEntity = usersPayService.getByOrderNumber(orderNumber);
        UsersPayDTO usersPayDTO = UsersPayConvert.INSTANCE.usersPayEntityToUsersPayDTO(usersPayEntity);
        return ResultModel.success(usersPayDTO);
    }

    /**
     * 获取待审核的订单列表
     *
     * @return
     */
    @RequestMapping(value = "/getCheckList", method = RequestMethod.GET)
    public ResultModel getCheckList() {
        List<UsersPayEntity> usersPayEntityS = usersPayService.selectAllByState(UsersPayStateEnum.TO_BE_AUDITED.getValue());
        List<UsersPayDTO> usersPayDTOS = UsersPayConvert.INSTANCE.usersPayEntityToUsersPayDTOList(usersPayEntityS);
        return ResultModel.success(usersPayDTOS);
    }


    /**
     * 获取未进行支付的订单列表
     *
     * @return
     */
    @RequestMapping(value = "/getNoPayList", method = RequestMethod.GET)
    public ResultModel getNoPayList() {
        List<UsersPayEntity> usersPayEntityS = usersPayService.selectAllByState(UsersPayStateEnum.SWEEP_CODE.getValue());
        List<UsersPayDTO> usersPayDTOS = UsersPayConvert.INSTANCE.usersPayEntityToUsersPayDTOList(usersPayEntityS);
        return ResultModel.success(usersPayDTOS);
    }

    /**
     * 获取未通过审核的订单列表
     *
     * @return
     */
    @RequestMapping(value = "/getRejectList", method = RequestMethod.GET)
    public ResultModel getRejectList() {
        List<UsersPayEntity> usersPayEntityS = usersPayService.selectAllByState(UsersPayStateEnum.REJECT.getValue());
        List<UsersPayDTO> usersPayDTOS = UsersPayConvert.INSTANCE.usersPayEntityToUsersPayDTOList(usersPayEntityS);
        return ResultModel.success(usersPayDTOS);
    }

    /**
     * 获取订单支付状态
     *
     * @return
     */
    @RequestMapping(value = "/getPayState", method = RequestMethod.GET)
    public ResultModel getPayState(String orderNumber) {
        if (StringUtils.isEmpty(orderNumber)) {
            return ResultModel.errorNoData("参数错误");
        }
        UsersPayEntity usersPayEntity = usersPayService.getStateByOrderNumber(orderNumber);
        UsersPayDTO usersPayDTO = UsersPayConvert.INSTANCE.usersPayEntityToUsersPayDTO(usersPayEntity);
        usersPayDTO.setStateStr(UsersPayStateEnum.getByValue(usersPayEntity.getState()).getName());
        return ResultModel.success(usersPayDTO);
    }



    /**
     * 驳回订单
     *
     * @return
     */
    @RequestMapping(value = "/payBack", method = RequestMethod.POST)
    public ResultModel payBack(String orderNumber) {
        if (StringUtils.isEmpty(orderNumber)) {
            return ResultModel.errorNoData("参数错误");
        }

        UsersPayEntity usersPayEntity = usersPayService.getByOrderNumber(orderNumber);
        if (usersPayEntity == null) {
            return ResultModel.errorNoData("参数错误");
        }
        if (!UsersPayStateEnum.TO_BE_AUDITED.getValue().equals(usersPayEntity.getState())) {
            return ResultModel.errorNoData("订单状态错误");
        }

        //修改支付信息的状态
        UsersPayEntity updateUsersPay = new UsersPayEntity();
        updateUsersPay.setId(usersPayEntity.getId());
        updateUsersPay.setState(UsersPayStateEnum.REJECT.getValue());
        usersPayService.updateById(updateUsersPay);
        return ResultModel.success("success");
    }

    /**
     * 分页获取订单
     *
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResultModel page(PageDTO pageDTO) {
        if (pageDTO == null) {
            return ResultModel.errorNoData("参数错误");
        }

        UsersPayQueryBo usersPayQueryBo = new UsersPayQueryBo();
        usersPayQueryBo.setUsersId(pageDTO.getUsersId());
        IPage<UsersPayEntity> entityIPage = usersPayService.getPage(pageDTO.getCurrentIndex(), pageDTO.getPageSize(), usersPayQueryBo);

        List<UsersPayDTO> usersPayDTOS = UsersPayConvert.INSTANCE.usersPayEntityToUsersPayDTOList(entityIPage.getRecords());
        Page<UsersPayDTO> usersPayDTOPage = BeanConvertUtils.pageConvert(entityIPage, usersPayDTOS);
        return ResultModel.success(usersPayDTOPage);
    }


}
