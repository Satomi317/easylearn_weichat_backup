package com.easylearn.modules.web.course.courseList.domain;

/**
 * Created by CZH on 2017/6/25.
 */
public class CourseListDomain {

    private int chapterNum;
    private String chapterTitle;
    private int courseNum;

    public int getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(int courseNum) {
        this.courseNum = courseNum;
    }

    public int getChapterNum() {
        return chapterNum;
    }

    public void setChapterNum(int chapterNum) {
        this.chapterNum = chapterNum;
    }

    public String getChapterTitle() {
        return chapterTitle;
    }

    public void setChapterTitle(String chapterTitle) {
        this.chapterTitle = chapterTitle;
    }
}
