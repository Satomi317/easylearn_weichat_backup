package com.easylearn.test.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.test.service.TestQuartzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by yuisama on 2017/7/5.
 */
@Controller
public class TestQuartzController extends MvcComponent{
    @Autowired
    private TestQuartzService testQuartzService;

//    @RequestMapping("/TestQuartz")
//    @ResponseBody
//    public String TestQuartz(){
//        testQuartzService.cronJob();
//        return "定时器启动成功";
//    }
}
