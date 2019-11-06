package com.uifuture.ssm.spring.ioc.demo14_6.ioc.impl;

import com.uifuture.ssm.spring.ioc.demo14_6.ioc.Pay;

/**
 * 支付基类
 */
public class PayImpl implements Pay {
    private Pay pay;

    public static void main(String[] args) {
        PayImpl pay = new PayImpl();
        pay.setPay(new WxPayImpl());
        pay.pay();
    }

    public void setPay(Pay pay) {
        this.pay = pay;
    }

    @Override
    public void pay() {
        pay.pay();
    }
}
