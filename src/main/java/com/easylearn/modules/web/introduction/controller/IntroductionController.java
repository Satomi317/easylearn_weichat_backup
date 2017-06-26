package com.easylearn.modules.web.introduction.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.introduction.service.IntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by CZH on 2017/6/19.
 */
@Controller
public class IntroductionController extends MvcComponent {
    @Autowired
    private IntroductionService introductionService;

    @RequestMapping("/getAllIntroduction")
    @ResponseBody
    public String getAllCourseIntroduction(){
        String res = introductionService.getAllCourseIntroduction();
        return introductionService.getAllCourseIntroduction();
    }
}
