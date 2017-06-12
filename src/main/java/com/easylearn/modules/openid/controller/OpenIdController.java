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

    @RequestMapping("/login")
    @ResponseBody
    public void getOpenId(HttpServletRequest request, HttpServletResponse response)throws IOException{
        String code = request.getParameter("code");
        String state = request.getParameter("state");
        logger.info("code:" + code);
        logger.info("state:" + state);
        if (code != null && state != null) {
            String openId = openIdService.getOpenId(code);
            logger.info("OpenId is: " + openId);
            if (openId.equals("")){
                logger.error("openId获取出错");
                return;
            } else {
                switch (state){
                    case "index":
                        response.sendRedirect(index+openId);
                }
            }
        } else {
            logger.error("code或者state为空");
            return;
        }
    }
}
