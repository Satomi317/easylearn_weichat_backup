package com.easylearn.modules.web.course.courseDetail.doamin;

/**
 * Created by CZH on 2017/6/29.
 */
public class CoursePartDomain {
    private long partNum;
    private String partTitle;
    private int chapterNum;
//    private String audioPath;
//    private String imgPath;

//    public String getImgPath() {
//        return imgPath;
//    }
//
//    public void setImgPath(String imgPath) {
//        this.imgPath = imgPath;
//    }
//
//    public String getAudioPath() {
//        return audioPath;
//    }
//
//    public void setAudioPath(String audioPath) {
//        this.audioPath = audioPath;
//    }

    public long getPartNum() {
        return partNum;
    }

    public void setPartNum(long partNum) {
        this.partNum = partNum;
    }

    public String getPartTitle() {
        return partTitle;
    }

    public void setPartTitle(String partTitle) {
        this.partTitle = partTitle;
    }

    public int getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(int chapterNum) {
        this.chapterNum = chapterNum;
    }
}
