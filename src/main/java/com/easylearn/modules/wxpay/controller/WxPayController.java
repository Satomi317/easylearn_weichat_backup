package com.easylearn.modules.wxpay.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.comm.WXPayConstants;
import com.easylearn.modules.wxpay.service.WXPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by czh on 2017/8/5.
 */
@Controller
public class WxPayController extends MvcComponent {

    @Autowired
    private WXPayService wxPaySerive;

    @RequestMapping("/payTest")
    @ResponseBody
    public String unifiedOrder(HttpServletRequest request, HttpServletResponse response)throws IOException {
        String  spbillCreateIp = request.getRemoteAddr();
        Map reqData = new HashMap<String, String>();
        try{
            HttpSession session = request.getSession();
            String openId = (String) session.getAttribute("openId");
            String totalFee = request.getParameter("totalFee");
            String prepayId = wxPaySerive.unifiedOrder(reqData,spbillCreateIp,totalFee,openId);
            logger.info("prepayId="+prepayId);

            String appId = request.getParameter("appId");
            String timeStamp = request.getParameter("timeStamp");
            String nonceStr = request.getParameter("nonceStr");
            logger.info("timeStamp="+timeStamp);
            logger.info("nonceStr="+nonceStr);
            logger.info("appId="+appId);
            String packAge = "prepay_id=" + prepayId;;

            Map webData = new HashMap<String, String>();

            webData.put("appId", appId);
            webData.put("nonceStr", nonceStr);
            webData.put("signType", "MD5");
            webData.put("package",packAge);
            webData.put("timeStamp",timeStamp);

            String paySign = wxPaySerive.createSign(webData);
            logger.info("paySign="+paySign);

            String jsonStr = "{\"packAge\":\"" + packAge +"\","+ "\"paySign\":\"" + paySign + "\"}";
            logger.info("jsonStr="+jsonStr);
            return jsonStr;
        }catch (Exception e){
            logger.error(e);
            return null;
        }
    }

    @RequestMapping("/payFeedback")
    @ResponseBody
    public String payFeedback(HttpServletRequest request, HttpServletResponse response){
        return "<xml> <return_code><![CDATA[SUCCESS]]></return_code>";
    }

    @RequestMapping("/createSign")
    @ResponseBody
    public String createSign(HttpServletRequest request, HttpServletResponse response){
        logger.info("create sign");
        String appId = request.getParameter("appId");
        String timeStamp = request.getParameter("timeStamp");
        String nonceStr = request.getParameter("nonceStr");
        String packAge = request.getParameter("package");

        Map reqData = new HashMap<String, String>();

        reqData.put("appId", appId);
        reqData.put("nonceStr", nonceStr);
        reqData.put("signType", "MD5");
        reqData.put("package",packAge);
        reqData.put("timeStamp",timeStamp);

        return wxPaySerive.createSign(reqData);
    }
}
