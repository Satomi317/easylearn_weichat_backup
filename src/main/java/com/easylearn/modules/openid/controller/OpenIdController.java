package com.easylearn.modules.openid.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.openid.service.OpenIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by yuisama on 2017/6/12.
 */
@Controller
public class OpenIdController extends MvcComponent {
    @Autowired
    private OpenIdService openIdService;

    @Value("${index}")
    public String index;

    @Value("${pretest}")
    public String pretest;

    @Value("${userinfo}")
    public String userinfo;

    @Value("${bonus}")
    public String bonus;

    @Value("${zeroBase}")
    public String zeroBase;

    @Value("${tinyBase}")
    public String tinyBase;

    @Value("${introduction}")
    public String introduction;

    @Value("${homework}")
    public String homework;

    @RequestMapping("/login")
    @ResponseBody
    public void getOpenId(HttpServletRequest request, HttpServletResponse response)throws IOException{
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        logger.info("code:" + code);
        logger.info("state:" + state);
        if (code != null && state != null) {
            String openId = openIdService.getOpenId(code);

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


            logger.info("OpenId is: " + openId);
            if (openId==null || openId == ""){
                logger.error("openId获取出错");
                return;
            } else {
                switch (state){
                    case "index":
                        response.sendRedirect(index+openId);
                        break;
                    case "pretest":
                        response.sendRedirect(pretest+openId);
                        break;
                    case "userinfo":
                        response.sendRedirect(userinfo+openId);
                        break;
                    case "bonus":
                        response.sendRedirect(bonus+openId);
                        break;
                    case "introduction":
                        response.sendRedirect(introduction+openId);
                        break;
                    case "zeroBase":
                        response.sendRedirect(zeroBase);
                        break;
                    case "tinyBase":
                        response.sendRedirect(tinyBase);
                        break;
                    case "homework":
                        response.sendRedirect(homework+openId);
                        break;
                }
            }
        } else {
            logger.error("code或者state为空");
            return;
        }
    }
}
