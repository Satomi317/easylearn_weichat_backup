package com.easylearn.modules.baseservice.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.baseservice.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by yuisama on 2017/5/27.
 * 微信后台总入口
 */
@Controller
public class CoreController extends MvcComponent{
    @Autowired
    private CoreService coreService;

    @Value("${token}")
    private String token;

    @RequestMapping(value = "/weChat",method = RequestMethod.GET)
    public void checkSignToW(HttpServletRequest request, HttpServletResponse response){
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        response.setHeader("Content-type", "text/html;charset=UTF-8");//指定消息头以UTF-8码表读数据
        PrintWriter out = null;
        //加密校验
        try{
            out = response.getWriter();
        }catch (Exception e){
            logger.error(e);
        }
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (coreService.checkSignature(signature, timestamp, nonce,token)) {
            logger.info(echostr);
            out.print(echostr);
        }
        out.close();
    }

    @RequestMapping(value = "weChat",method = RequestMethod.POST)
    public void processRequest(HttpServletRequest request,HttpServletResponse response)throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String resp = coreService.processRequest(request);
        logger.info(resp);
        PrintWriter out = response.getWriter();
        out.print(resp);
        out.close();
    }

    @RequestMapping("/createMenu")
    @ResponseBody
    public String creatMenu(){
        return coreService.createMenu();
    }

    @RequestMapping("/getJsTicket")
    @ResponseBody
    public String getJsTicket(HttpServletRequest request){
        String url = request.getParameter("url");
        return coreService.getSignatureInfo(coreService.getNonceStr(),coreService.getJsTicket(),coreService.getTimeStamp(),url);
    }


}
