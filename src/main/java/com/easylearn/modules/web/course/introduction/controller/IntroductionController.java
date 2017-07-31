package com.easylearn.modules.web.course.introduction.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.course.introduction.service.IntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by CZH on 2017/6/19.
 */
@Controller
public class IntroductionController extends MvcComponent {
    @Autowired
    private IntroductionService introductionService;

    /**
     * 获取全部课程的介绍信息
     * @return
     */
    @RequestMapping("/getAllIntroduction")
    @ResponseBody
    public String getAllCourseIntroduction(){
        String res = introductionService.getAllCourseIntroduction();
        return res;
    }

    /**
     * 获取指定课程的介绍信息
     * @return
     */
    @RequestMapping(value = "/getIntroduction",method = RequestMethod.GET)
    @ResponseBody
    public String getCourseIntroduction(HttpServletRequest request){
        String courseNumParm = request.getParameter("courseNum");

        String res = introductionService.getCourseIntroduction(courseNumParm);
        return res;
    }

    /**
     * 根据chapterNum 获取指定课程的介绍信息
     * @return
     */
    @RequestMapping(value = "/getIntroductionByChapter",method = RequestMethod.GET)
    @ResponseBody
    public String getCourseIntroductionByChapterNum(HttpServletRequest request){
        String courseNumParm = request.getParameter("chapterNum");
        String res = introductionService.getCourseIntroductionByChapter(courseNumParm);
        return res;
    }
}
