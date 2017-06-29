package com.easylearn.modules.web.course.courseDetail.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.course.courseDetail.service.CourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CZH on 2017/6/29.
 */
@Controller
public class CourseDetailController extends MvcComponent {
    @Autowired
    CourseDetailService courseDetailService;

    @RequestMapping("/getCourseDetail")
    @ResponseBody
    public String getCourseDetail(HttpServletRequest request){
        try {
            String chapterNumParm = request.getParameter("chapterNum");
            int chapterNum = Integer.parseInt(chapterNumParm);
            String res = courseDetailService.getCourseDetail(chapterNum);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
