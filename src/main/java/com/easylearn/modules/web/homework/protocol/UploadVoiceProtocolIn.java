package com.easylearn.modules.web.homework.protocol;

/**
 * Created by CZH on 2017/6/30.
 */
public class UploadVoiceProtocolIn {
    private String localId;
    private long chapterNum;
    private String timetamp;


    public long getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(long chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getTimetamp() {
        return timetamp;
    }

    public void setTimetamp(String timetamp) {
        this.timetamp = timetamp;
    }

    public String getLocalId() {

        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }
}
