package com.easylearn.modules.web.course.courseList.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.course.courseList.dao.CourseListDao;
import com.easylearn.modules.web.course.courseList.domain.CourseListDomain;
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

    public String getPreviousChapter(int courseNum,int chapterNum){
        List<CourseListDomain> result = courseListDao.getCourseList(courseNum);
        CourseListDomain prevChapter = new CourseListDomain();
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
}
