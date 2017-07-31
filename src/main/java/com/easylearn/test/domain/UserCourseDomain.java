package com.easylearn.test.domain;

/**
 * Created by czh on 2017/7/29.
 */
public class UserCourseDomain {
    private String openId;
    private String courseType;
    private long startTime;
    private long expiryTime;
    private int pushNumber;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(long expiryTime) {
        this.expiryTime = expiryTime;
    }

    public int getPushNumber() {
        return pushNumber;
    }

    public void setPushNumber(int pushNumber) {
        this.pushNumber = pushNumber;
    }

}
