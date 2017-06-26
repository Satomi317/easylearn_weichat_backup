package com.easylearn.modules.web.introduction.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.introduction.dao.IntroductionDao;
import com.easylearn.modules.web.introduction.domain.CourseIntroductionDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<CourseIntroductionDomain> introductions = introductionDao.getCourseIntroduction();
        return gson.toJson(introductions);
    }
}
