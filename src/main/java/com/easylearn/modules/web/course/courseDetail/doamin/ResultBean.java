package com.easylearn.modules.web.course.courseDetail.doamin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CZH on 2017/6/29.
 */
public class ResultBean {
    private String partTitle;
//    private String imgUrl;
//    private String audioPath;
    private List audioContent;
    private List audioList;

    public List getAudioList() {
        return audioList;
    }

    public void setAudioList(List audioList) {
        this.audioList = audioList;
    }

    public String getPartTitle() {
        return partTitle;
    }

    public void setPartTitle(String partTitle) {
        this.partTitle = partTitle;
    }

//    public String getImgUrl() {
//        return imgUrl;
//    }
//
//    public void setImgUrl(String imgUrl) {
//        this.imgUrl = imgUrl;
//    }
//
//    public String getAudioPath() {
//        return audioPath;
//    }
//
//    public void setAudioPath(String audioPath) {
//        this.audioPath = audioPath;
//    }

    public List getAudioContent() {
        return audioContent;
    }

    public void setAudioContent(List audioContent) {
        this.audioContent = audioContent;
    }
}
