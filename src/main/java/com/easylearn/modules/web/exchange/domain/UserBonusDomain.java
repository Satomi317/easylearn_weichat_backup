package com.easylearn.modules.web.exchange.domain;

/**
 * Created by czh on 2017/8/21.
 */
public class UserBonusDomain {
    private String openId;
    private int bonus;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
