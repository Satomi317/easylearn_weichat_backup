package com.easylearn.modules.web.courseList.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.courseList.dao.CourseListDao;
import com.easylearn.modules.web.courseList.domain.CourseListDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CZH on 2017/6/25.
 */
@Service
public class CourseListService   extends MvcComponent {
    @Autowired
    private CourseListDao courseListDao;

    public String getCourseList(int courseNum){
        List<CourseListDomain> result = courseListDao.getCourseList(courseNum);
        return gson.toJson(result);
    }
}
