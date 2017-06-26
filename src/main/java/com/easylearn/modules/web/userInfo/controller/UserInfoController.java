package com.easylearn.modules.web.userInfo.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.userInfo.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by CZH on 2017/6/26.
 */
@Controller
public class UserInfoController extends MvcComponent {
    @Autowired
    private UserInfoService userInfoService;


    @RequestMapping("/getUserBonus")
    @ResponseBody
    public String getUserBonus(HttpServletRequest request){
            HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");
        openId = "oJGSo04cMyHzXwpvnwrjcMLJ0Ky8";
        if(openId != null && openId != ""){
            return userInfoService.getUserBonus(openId);
        }else{
            return "{\"result\":\"openId is null!\"}";
        }
    }
    @RequestMapping("/getUserCourse")
    @ResponseBody
    public String getUserCourse(HttpServletRequest request){
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");
        openId = "oJGSo04cMyHzXwpvnwrjcMLJ0Ky8";
        if(openId != null && openId != ""){
            return userInfoService.getUserCourse(openId);
        }else{
            return "{\"result\":\"openId is null!\"}";
        }
    }

    @RequestMapping("/getUserInfo")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");
        openId = "oJGSo04cMyHzXwpvnwrjcMLJ0Ky8";
        if(openId != null && openId != ""){
            return userInfoService.getUserInfo(openId);
        }else{
            return "{\"result\":\"openId is null!\"}";
        }
    }
}
