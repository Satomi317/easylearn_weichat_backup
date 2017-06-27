package com.easylearn.modules.web.exchange.protocol;

/**
 * Created by CZH on 2017/6/27.
 */
public class ProtocolIn {
    private String signature;
    private int exchangeDay;
    private int exchangeItem;

    public int getExchangeItem() {
        return exchangeItem;
    }

    public void setExchangeItem(int exchangeItem) {
        this.exchangeItem = exchangeItem;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getExchangeDay() {
        return exchangeDay;
    }

    public void setExchangeDay(int exchangeDay) {
        this.exchangeDay = exchangeDay;
    }
}
