package com.easylearn.modules.web.course.courseList.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.course.courseList.dao.CourseListDao;
import com.easylearn.modules.web.course.courseList.domain.CourseListDomain;
import com.easylearn.test.domain.UserCourseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CZH on 2017/6/25.
 */
@Service
public class CourseListService extends MvcComponent {
    @Autowired
    private CourseListDao courseListDao;

    //获取课程列表
    public String getCourseList(int courseNum, int pushNumber){
        List<CourseListDomain> result = courseListDao.getCourseList(courseNum, pushNumber);
        return gson.toJson(result);
    }

    //获取前一节课
    public String getPreviousChapter(int chapterNum){
        List<CourseListDomain> result = courseListDao.getCourseListByChapterNum(chapterNum);
        int indexCur = 0;
        for(int i = 0; i < result.size();i++){
            if(result.get(i).getChapterNum() == chapterNum){
                indexCur = i;
                break;
            }
        }
        if(indexCur == 0){
            return "{\"success\":false}";
        }else{
            return gson.toJson(result.get(indexCur-1));
        }
    }

    //获取用户课程的购买信息
    public UserCourseDomain getUserCourseInfo(String openId,int courseNum){
        //courseNum和courseType的对应
        String courseType = "";
        if(courseNum == 3){
            courseType = "0";
        }else if(courseNum == 4){
            courseType = "1";
        }
        List<UserCourseDomain> userCourseInfo =  courseListDao.getUserCourse(openId,courseType);
        if(userCourseInfo.size() == 0){
            return null;
        }else{
            return userCourseInfo.get(0);
        }
    }
}
