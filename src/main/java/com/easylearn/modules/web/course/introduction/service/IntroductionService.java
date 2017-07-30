package com.easylearn.modules.web.course.introduction.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.course.introduction.dao.IntroductionDao;
import com.easylearn.modules.web.course.introduction.domain.CourseIntroductionDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CZH on 2017/6/19.
 */
@Service
public class IntroductionService  extends MvcComponent {
    @Autowired
    private IntroductionDao introductionDao;

    /**
     * 获取全部课程的介绍
     * @return
     */
    public String getAllCourseIntroduction(){
        List<CourseIntroductionDomain> introductions = introductionDao.getAllCourseIntroduction();
        return gson.toJson(introductions);
    }

    /**
     * 获取指定课程的介绍
     * @return
     */
    public String getCourseIntroduction(String courseNum){
        CourseIntroductionDomain introductions = introductionDao.getCourseIntroduction(courseNum);
        return gson.toJson(introductions);
    }

    /**
     * 获取指定课程的介绍
     * @return
     */
    public String getCourseIntroductionByChapter(String chapterNum){
        CourseIntroductionDomain introductions = introductionDao.getCourseIntroductionByChapter(chapterNum);
        return gson.toJson(introductions);
    }
}
