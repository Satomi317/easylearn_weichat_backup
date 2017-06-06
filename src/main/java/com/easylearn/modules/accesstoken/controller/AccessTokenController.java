package com.easylearn.modules.accesstoken.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.accesstoken.service.AccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yuisama on 2017/5/15.
 */
@Controller
public class AccessTokenController extends MvcComponent{
    @Autowired
    private AccessTokenService accessTokenService;

    @RequestMapping("/accessToken")
    @ResponseBody
    public String getAccessToken(){
        return accessTokenService.getAccessToken();
    }
}
