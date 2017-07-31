package com.easylearn.modules.web.course.courseList.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.course.courseList.service.CourseListService;
import com.easylearn.test.domain.UserCourseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by CZH on 2017/6/25.
 */
@Controller
public class CourseListController extends MvcComponent {
    @Autowired
    private CourseListService courseListService;

    @RequestMapping(value = "/getCourseList",method = RequestMethod.GET)
    @ResponseBody
    public String getCourseList(HttpServletRequest request){
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");
        //openId = "oJGSo04cMyHzXwpvnwrjcMLJ0Ky8";
        logger.info("openId from session:"+openId);
        String courseNumParm = request.getParameter("courseNum");
        try {
            int courseNum = Integer.parseInt(courseNumParm);
            UserCourseDomain userCourseInfo = courseListService.getUserCourseInfo(openId,courseNum);
            if(userCourseInfo == null){
                return "{\"success\":false}";
            }else{
                return courseListService.getCourseList(courseNum,userCourseInfo.getPushNumber());
            }
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping("/getPreviousChapter")
    @ResponseBody
    public String getPreviousChapter(HttpServletRequest request){
        String chapterNumParm = request.getParameter("chapterNum");
        try {
            int chapterNum = Integer.parseInt(chapterNumParm);
            return courseListService.getPreviousChapter(chapterNum);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
