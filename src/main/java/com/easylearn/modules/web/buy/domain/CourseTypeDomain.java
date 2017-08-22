package com.easylearn.modules.web.buy.domain;

/**
 * Created by czh on 2017/8/5.
 */
public class CourseTypeDomain {
    private int id;
    private String courseType;
    private String courseName;
    private float coursePrice;
    private int courseDays;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public float getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(float coursePrice) {
        this.coursePrice = coursePrice;
    }

    public int getCourseDays() {
        return courseDays;
    }

    public void setCourseDays(int courseDays) {
        this.courseDays = courseDays;
    }
}
