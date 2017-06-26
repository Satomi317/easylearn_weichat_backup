package com.easylearn.modules.web.courseList.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.courseList.service.CourseListService;
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
        logger.info("openId from session:"+openId);
        String courseNumParm = request.getParameter("courseNum");
        try {
            int courseNum = Integer.parseInt(courseNumParm);
            return courseListService.getCourseList(courseNum);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
