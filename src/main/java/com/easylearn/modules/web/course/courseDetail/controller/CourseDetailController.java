package com.easylearn.modules.web.course.courseDetail.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.course.courseDetail.service.CourseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
            String openId = request.getParameter("openId");
            //如果url中传了openId，加入session
            if(openId != null && openId != ""){
                //使用request对象的getSession()获取session，如果session不存在则创建一个
                HttpSession session = request.getSession();
                //将数据存储到session中
                session.setAttribute("openId", openId);
                //获取session的Id
                String sessionId = session.getId();
                //判断session是不是新创建的
                if (session.isNew()) {
                    logger.info("session创建成功，session的id是："+sessionId);
                }else {
                    logger.info("服务器已经存在该session了，session的id是："+sessionId);
                }
            }


            int chapterNum = Integer.parseInt(chapterNumParm);
            String res = courseDetailService.getCourseDetail(chapterNum);
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }
}
