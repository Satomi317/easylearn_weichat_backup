package com.easylearn.modules.wxpay.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.comm.WXPayConfig;
import com.easylearn.comm.WXPayConstants;
import com.easylearn.comm.WXPayUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.easylearn.comm.WXPayUtil;
import com.easylearn.comm.WXPayConfig;
import com.easylearn.comm.WXPayConstants.SignType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;


/**
 * Created by czh on 2017/8/5.
 */
@Service
public class WXPayService  extends MvcComponent {

    private WXPayConfig config;
    private WXPayConstants.SignType signType;
    private WXPayUtil wxPayUtil;
    /**
     * 向 Map 中添加 appid、mch_id、nonce_str、sign_type、sign <br>
     * 该函数适用于商户适用于统一下单等接口，不适用于红包、代金券接口
     *
     * @param reqData
     * @return
     * @throws Exception
     */
    public Map<String, String> fillRequestData(Map<String, String> reqData,String spbillCreateIp,String totalFee,String openId) throws Exception {

        reqData.put("appid", "wxd595883477646872");
        reqData.put("mch_id", "1482302642");
        reqData.put("nonce_str", WXPayUtil.generateUUID());
        reqData.put("sign_type", WXPayConstants.MD5);
        reqData.put("body","说吧课程购买");
        reqData.put("openid",openId);
        reqData.put("out_trade_no",WXPayUtil.generateUUID());
        reqData.put("total_fee",totalFee);
        reqData.put("spbill_create_ip",spbillCreateIp);
        reqData.put("notify_url","http://www.shuobaba.com/payFeedback");
        reqData.put("trade_type","JSAPI");

        this.signType = SignType.MD5;
        reqData.put("sign", WXPayUtil.generateSignature(reqData, "10f021e6a69181dac6519879166565cf", this.signType));
        logger.info(reqData);
        return reqData;
    }


    /**
     * 统一下单
     * @param reqData
     * @return
     * @throws Exception
     */
    public String unifiedOrder(Map<String, String> reqData, String spbillCreateIp, String totalFee,String openId) throws Exception{
        //装填数据
        reqData = fillRequestData(reqData,spbillCreateIp,totalFee,openId);
        //Map转XML
        String xml = WXPayUtil.mapToXml(reqData);
        //向订单平台发送下单请求
        String prepayId = sendXml(xml,"https://api.mch.weixin.qq.com/pay/unifiedorder");

        return prepayId;
    }

    /**
     * 发送XML
     * @param xml
     * @param urlStr
     * @return  prepay_id
     */
    public String sendXml(String xml,String urlStr){
        try {
            logger.info(xml);
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/xml; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());
            HttpEntity<String> formEntity = new HttpEntity<String>(xml, headers);
            String respFormWx = restTemplate.postForObject(urlStr,formEntity,String.class);
            logger.info("微信回应为："+respFormWx);

            int startIndex = respFormWx.indexOf("<prepay_id><![CDATA[") + "<prepay_id><![CDATA[".length();
            int endIndex = respFormWx.indexOf("]]></prepay_id>");

            return respFormWx.substring(startIndex, endIndex);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return null;
        }
    }

    public String createSign(Map<String, String> data){
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append("10f021e6a69181dac6519879166565cf");
        String sign;
        try {
            sign = wxPayUtil.MD5(sb.toString()).toUpperCase();
            logger.info("sign="+sign);
        }catch (Exception e){
            return null;
        }
        return sign;
    }
}
