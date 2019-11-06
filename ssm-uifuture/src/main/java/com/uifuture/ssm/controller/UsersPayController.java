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
    private static final String notifyUrl = "http://127.0.0.1:8888/users-pay-entity/alipay/notify";

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
     * 异步发送通知邮件
     * @param outTradeNo
     */
    @Async
    public void sendActiveEmail(String outTradeNo){

    }


}
