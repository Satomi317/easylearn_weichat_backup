package com.easylearn.modules.web.exchange.domain;

/**
 * Created by CZH on 2017/6/27.
 */
public class BonusListDomain {
    private int bonusNum;
    private int day;
    private long bonus;

    public int getBonusNum() {
        return bonusNum;
    }

    public void setBonusNum(int bonusNum) {
        this.bonusNum = bonusNum;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public long getBonus() {
        return bonus;
    }

    public void setBonus(long bonus) {
        this.bonus = bonus;
    }
}
