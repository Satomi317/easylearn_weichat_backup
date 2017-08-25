package com.easylearn.modules.web.homework.domain;

/**
 * Created by CZH on 2017/6/30.
 */
public class HomeworkDomain {
    private String localId;
    private String openId;
    private String timetamp;
    private long chapterNum;
    private String chapterTitle;

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getTimetamp() {
        return timetamp;
    }

    public void setTimetamp(String timetamp) {
        this.timetamp = timetamp;
    }

    public long getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(long chapterNum) {
        this.chapterNum = chapterNum;
    }
}
