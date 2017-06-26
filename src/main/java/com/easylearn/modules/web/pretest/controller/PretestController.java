package com.easylearn.modules.web.pretest.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.pretest.service.PretestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by CZH on 2017/6/17.
 */
@Controller
public class PretestController extends MvcComponent {
    @Autowired
    private PretestService pretestService;
    /**
     * 从题库中抽取题目
     * @return
     */
    @RequestMapping("/getQuestions")
    @ResponseBody
    public String getQuestions(){
        return pretestService.getQuestions();
    }
}