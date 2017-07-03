package com.easylearn.modules.baseservice.beans;

/**
 * Created by yuisama on 2017/7/3.
 */
public class TextSubscribe {
    private String touser;
    private String msgtype;
    private TextSubContent text;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public TextSubContent getText() {
        return text;
    }

    public void setText(TextSubContent text) {
        this.text = text;
    }
}
