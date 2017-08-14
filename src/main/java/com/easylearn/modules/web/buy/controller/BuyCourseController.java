package com.easylearn.modules.web.buy.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.buy.service.BuyCourseService;
import com.easylearn.modules.web.exchange.protocol.ProtocolOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by czh on 2017/8/2.
 */
@Controller
public class BuyCourseController extends MvcComponent {

    @Autowired
    private BuyCourseService buyCourseService;

    /**
     * 购买课程
     * @param request
     * @return
     */
    @RequestMapping("/buyCourse")
    @ResponseBody
    public String buyCourse(HttpServletRequest request){
        ProtocolOut result = new ProtocolOut();

        try {
            String buyId = request.getParameter("buyId");
            String inviteCode = request.getParameter("inviteCode");
            //从session获取用户的openId
            HttpSession session = request.getSession();
            String openId = (String) session.getAttribute("openId");
            if(openId != null && openId != ""){
                //在user_course表中添加用户的记录
                if(buyCourseService.buyCourse(openId,buyId)){
                    result.setMessage("购买成功");
                    result.setSuccess(true);
                    result.setResult("");
                }else{
                    result.setMessage("购买失败");
                    result.setSuccess(false);
                    result.setResult("");
                }

            }else{
                result.setMessage("error:用户未登录,session信息不存在。");
                result.setSuccess(false);
                result.setResult("");
            }

            return gson.toJson(result);
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @RequestMapping("/sellCourseList")
    @ResponseBody
    public String sellCourseList(HttpServletRequest request){
        String courseType = request.getParameter("courseType");
        return buyCourseService.getAllSellCourse(courseType);
    }


    /**
     * 返回用户是否购买此类课程
     * @param request
     * @return
     */
    @RequestMapping("/hasBuy")
    @ResponseBody
    public String hasBuy(HttpServletRequest request){
        ProtocolOut result = new ProtocolOut();
        //购买课程的ID
        String courseType = request.getParameter("courseType");

        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");
        if(openId != null && openId != ""){
            if(buyCourseService.hasBuy(openId,courseType) == false){
                result.setMessage("用户未购买该课程！");
                result.setSuccess(true);
                result.setResult("false");
                logger.info("用户未购买该课程");
            }else{
                result.setMessage("用户已购买该课程！");
                result.setSuccess(true);
                result.setResult("true");
                logger.info("用户已购买该课程");
            }
        }else{
            result.setMessage("error:用户未登录,session信息不存在。");
            result.setSuccess(false);
            result.setResult("");
        }
        return gson.toJson(result);
    }
}
