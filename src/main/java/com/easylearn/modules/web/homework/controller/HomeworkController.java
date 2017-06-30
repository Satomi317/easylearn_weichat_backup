package com.easylearn.modules.web.homework.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.exchange.protocol.ProtocolOut;
import com.easylearn.modules.web.homework.protocol.UploadVoiceProtocolIn;
import com.easylearn.modules.web.homework.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by CZH on 2017/6/30.
 */
@Controller
public class HomeworkController extends MvcComponent {
    @Autowired
    private HomeworkService homeworkService;

    @RequestMapping(value = "uploadVoice",method = RequestMethod.POST)
    public void uploadVoice(@RequestBody UploadVoiceProtocolIn uploadVoiceProtocolIn, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        ProtocolOut result = new ProtocolOut();

        //从session获取用户的openId
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");
        if(openId != null && openId != ""){
            String localId = uploadVoiceProtocolIn.getLocalId();
            long chapterNum = uploadVoiceProtocolIn.getChapterNum();
            String timetamp = uploadVoiceProtocolIn.getTimetamp();
            homeworkService.insertHomework(localId,openId,timetamp,chapterNum);
            result.setMessage("插入成功");
            result.setSuccess(true);
            result.setResult("");
        }else{
            result.setMessage("error:用户未登录,session信息不存在。");
            result.setSuccess(false);
            result.setResult("");
        }
        //发送响应
        String resp = gson.toJson(result);
        logger.info(resp);
        PrintWriter out = response.getWriter();
        out.print(resp);
        out.close();
    }

    @RequestMapping("/getUserHomework")
    @ResponseBody
    public String getUserHomework(HttpServletRequest request){
        //从session获取用户的openId
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");
        //openId = "oJGSo04cMyHzXwpvnwrjcMLJ0Ky8";
        if(openId != null && openId != ""){
            return homeworkService.getUserHomework(openId);
        }else{
            return "{\"result\":\"openId is null!\"}";
        }
    }
}
