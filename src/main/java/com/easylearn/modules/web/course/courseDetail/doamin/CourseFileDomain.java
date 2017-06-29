package com.easylearn.modules.web.course.courseDetail.doamin;

/**
 * Created by CZH on 2017/6/29.
 */
public class CourseFileDomain {
    private long fileNum;
    private long partNum;
    private String fileType;
    private String fileName;
    private String audioPath;

    public long getFileNum() {
        return fileNum;
    }

    public void setFileNum(long fileNum) {
        this.fileNum = fileNum;
    }

    public long getPartNum() {
        return partNum;
    }

    public void setPartNum(long partNum) {
        this.partNum = partNum;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAudioPath() {
        return audioPath;
    }

    public void setAudioPath(String audioPath) {
        this.audioPath = audioPath;
    }
}
