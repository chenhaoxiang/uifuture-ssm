package com.uifuture.ssm.spring.ioc.demo14_6.terrible;

/**
 * 最糟糕的写法
 */
public class PayImpl {
    public void pay(Integer type) {
        if (type.equals(1)) {
            new WxPay().pay();
        } else if (type.equals(2)) {
            new AliPay().pay();
        } else {
            System.out.println("非常抱歉，暂不支持该种支付方式，请使用微信或者支付宝进行支付。");
        }
    }

    class WxPay {
        public void pay() {
            System.out.println("微信支付");
        }
    }

    class AliPay {
        public void pay() {
            System.out.println("支付宝支付");
        }
    }
}
