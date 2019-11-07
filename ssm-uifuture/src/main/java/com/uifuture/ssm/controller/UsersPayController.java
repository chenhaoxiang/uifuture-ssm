package com.uifuture.ssm.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.uifuture.ssm.base.BaseController;
import com.uifuture.ssm.common.RedisConstants;
import com.uifuture.ssm.config.SysConfig;
import com.uifuture.ssm.convert.UsersPayConvert;
import com.uifuture.ssm.dto.PageDTO;
import com.uifuture.ssm.entity.UsersEntity;
import com.uifuture.ssm.entity.UsersPayEntity;
import com.uifuture.ssm.enums.PayTypeEnNameEnum;
import com.uifuture.ssm.redis.RedisClient;
import com.uifuture.ssm.req.UsersPayReq;
import com.uifuture.ssm.result.ResultModel;
import com.uifuture.ssm.service.UsersPayService;
import com.uifuture.ssm.util.PasswordUtils;
import com.uifuture.ssm.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
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
public class UsersPayController extends BaseController {

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
    private UsersPayService usersPayService;
    /**
     * 生成支付二维码
     *
     * @return
     */
    @RequestMapping(value = "/alipay/createQrCode", method = RequestMethod.POST)
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
        if(!response.isSuccess()){
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
    public ResultModel alipayNotify(@RequestParam(required = false,name = "out_trade_no") String outTradeNo,
                                    @RequestParam(required = false,name = "trade_status") String tradeStatus){
        if("TRADE_SUCCESS".equals(tradeStatus)){
            sendActiveEmail(outTradeNo);
        }
        return ResultModel.success("success");
    }

    /**
     * TODO 异步发送通知邮件
     * @param outTradeNo
     */
    @Async
    public void sendActiveEmail(String outTradeNo){
        //通过token控制，1天内有效
        String token = PasswordUtils.getSalt();


    }


    /**
     * TODO 根据订单号获取支付信息
     *
     * @return
     */
    @RequestMapping(value = "/getPayInfo", method = RequestMethod.GET)
    public ResultModel getPayInfo(String orderNumber) {
        if (StringUtils.isEmpty(orderNumber)) {
            return ResultModel.errorNoData("参数错误");
        }
        UsersPayEntity usersPayEntity = usersPayService.getByOrderNumber(orderNumber);
        return ResultModel.success(usersPayEntity);
    }

    /**
     * TODO 获取待审核的订单列表
     *
     * @return
     */
    @RequestMapping(value = "/getCheckList", method = RequestMethod.GET)
    public ResultModel getCheckList() {
        //管理员才能访问


        return ResultModel.success("success");
    }


    /**
     * TODO 获取未进行支付的订单列表
     *
     * @return
     */
    @RequestMapping(value = "/getNoPayList", method = RequestMethod.GET)
    public ResultModel getNoPayList() {
        //管理员才能访问


        return ResultModel.success("success");
    }

    /**
     * TODO 获取订单支付状态
     *
     * @return
     */
    @RequestMapping(value = "/getPayState", method = RequestMethod.GET)
    public ResultModel getPayState(String orderNumber) {
        if (StringUtils.isEmpty(orderNumber)) {
            return ResultModel.errorNoData("参数错误");
        }

        return ResultModel.success("success");
    }

    /**
     * TODO 订单支付审核通过
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

        return ResultModel.success("success");
    }


    /**
     * TODO 审核驳回支付订单
     *
     * @return
     */
    @RequestMapping(value = "/payBack", method = RequestMethod.POST)
    public ResultModel payBack(String orderNumber) {
        if (StringUtils.isEmpty(orderNumber)) {
            return ResultModel.errorNoData("参数错误");
        }

        return ResultModel.success("success");
    }

    /**
     * TODO 分页获取所有的订单
     *
     * @return
     */
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public ResultModel page(PageDTO pageDTO) {
        if (pageDTO == null) {
            return ResultModel.errorNoData("参数错误");
        }

        return ResultModel.success("success");
    }


}
