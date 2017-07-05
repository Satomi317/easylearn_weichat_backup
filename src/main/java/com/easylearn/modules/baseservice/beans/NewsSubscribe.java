package com.easylearn.modules.baseservice.beans;

/**
 * Created by yuisama on 2017/7/5.
 */
public class NewsSubscribe {
    private String touser;
    private String msgtype;
    private NewsContent news;

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

    public NewsContent getNews() {
        return news;
    }

    public void setNews(NewsContent news) {
        this.news = news;
    }
}
