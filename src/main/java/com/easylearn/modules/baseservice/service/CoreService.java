package com.easylearn.modules.baseservice.service;

import com.easylearn.comm.EncryptUtil;
import com.easylearn.comm.MessageUtil;
import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.accesstoken.service.AccessTokenService;
import com.easylearn.modules.baseservice.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Created by yuisama on 2017/5/27.
 * 微信后台入口类
 */
@Service
public class CoreService extends MvcComponent{
    @Autowired
    private AccessTokenService accessTokenService;

    @Value("${appId}")
    public String appId;

    @Value("${serverAddress}")
    public String serverAddress;


    private static String jsapi_ticket = "";
    private static Double jsapi_expires_in = 7200.0;
    private static long jsapi_getTime = 0;
    private static String nonce_str = "";
    private static String timestamp = "";

    /**
     * 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @return Boolean
     */
    public boolean checkSignature(String signature, String timestamp, String nonce, String Token) {
        String[] arr = new String[] { Token, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序
        try {
            Arrays.sort(arr);
        } catch (Exception e) {
            logger.error(e);
        }

        StringBuilder content = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        MessageDigest md = null;
        String tmpStr = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            // 将三个参数字符串拼接成一个字符串进行sha1加密
            byte[] digest = md.digest(content.toString().getBytes());
            tmpStr = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e);
        }
        content = null;
        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
    }

    /**
    * Author yuisama
    * @Date 2017/5/28 10:51
     * 按照请求中的msgType来处理消息
    */
    public String processRequest(HttpServletRequest request){
        logger.info("processRequest");
        String respMessage = null;
        // 默认返回的文本消息内容
        String respContent = "请求处理异常，请稍后尝试";
        try {
            Map<String,String> requestMap = MessageUtil.parseXml(request);
            //用户openId
            String openId = requestMap.get("FromUserName");
            //公众账号
            String toUserName = requestMap.get("ToUserName");
            //消息类型
            String msgType = requestMap.get("MsgType");

            // 文本消息
            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
                String content = requestMap.get("Content");
                if (!content.isEmpty()) {
                    // 自动回复文本消息
                     String Content = "欢迎关注说乎~";
                     TextMessage textMessage = createTextMessage(openId,toUserName,Content);
                     respMessage = MessageUtil.textMessageToXml(textMessage);
                }
            }
            //事件推送
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)){
                String eventType = requestMap.get("Event");
                logger.info("eventType:"+eventType);
                //订阅事件
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
                    String Content = "Hello~亲爱的小伙伴，欢迎加入说乎~请先进行<a href=\"https://www.baidu.com/\">"+"学前测试</a>";
                    TextMessage textMessage = createTextMessage(openId,toUserName,Content);
                    respMessage = MessageUtil.textMessageToXml(textMessage);
                }
                //点击事件
                else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)){
                    String eventKey = requestMap.get("EventKey");
                    logger.info("eventKey:"+eventKey);
                    if (eventKey.equals("customerService")){
                        String content = "亲，有问题请邮件说乎客服：liuyiming@quyiyuan.com";
                        TextMessage textMessage = createTextMessage(openId,toUserName,content);
                        respMessage = MessageUtil.textMessageToXml(textMessage);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return respMessage;
    }


    /**
    * Author yuisama
    * @Date 2017/5/29 11:57
     * 创建菜单
    */
    public String createMenu(){
        String accessToken = accessTokenService.getAccessToken();
        logger.info(accessToken);
        //关于说乎子菜单
        //学前测试
        CommProperty learnTest = new CommProperty();
        learnTest.setName("学前测试");
        learnTest.setType("view");
        learnTest.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + appId
                + "&redirect_uri=http://" + serverAddress
                + "/login&response_type=code&scope=snsapi_base&state=index#wechat_redirect/");
        //课程介绍
        CommProperty courseInfo = new CommProperty();
        courseInfo.setName("课程介绍");
        courseInfo.setType("view");
        courseInfo.setUrl("https://www.baidu.com");
        SubButton aboutEasyLearnSub = new SubButton();
        List<CommProperty> aboutEasyLearnList = new ArrayList<>();
        aboutEasyLearnList.add(learnTest);
        aboutEasyLearnList.add(courseInfo);
        aboutEasyLearnSub.setName("关于说乎");
        aboutEasyLearnSub.setSub_button(aboutEasyLearnList);

        //快速听课子菜单
        //试听
        CommProperty audition = new CommProperty();
        audition.setName("试听");
        audition.setType("view");
        audition.setUrl("https://www.baidu.com");
        //零基础
        CommProperty zeroBase = new CommProperty();
        zeroBase.setName("零基础");
        zeroBase.setType("view");
        zeroBase.setUrl("https://www.baidu.com");
        //微基础
        CommProperty BaseLearner = new CommProperty();
        BaseLearner.setName("微基础");
        BaseLearner.setType("view");
        BaseLearner.setUrl("https://www.baidu.com");
        SubButton quickListenSub = new SubButton();
        List<CommProperty> quickListenList = new ArrayList<>();
        quickListenList.add(audition);
        quickListenList.add(zeroBase);
        quickListenList.add(BaseLearner);
        quickListenSub.setName("快速听课");
        quickListenSub.setSub_button(quickListenList);

        //学员中心子菜单
        //学员信息
        CommProperty learnerInfo = new CommProperty();
        learnerInfo.setName("学员信息");
        learnerInfo.setType("view");
        learnerInfo.setUrl("https://www.baidu.com");
        //我的作业
        CommProperty myTask = new CommProperty();
        myTask.setName("我的作业");
        myTask.setType("view");
        myTask.setUrl("https://www.baidu.com");
        //积分兑换
        CommProperty creExchange = new CommProperty();
        creExchange.setName("积分兑换");
        creExchange.setType("view");
        creExchange.setUrl("https://www.baidu.com");
        //客服
        ClickMenuProperty customerService = new ClickMenuProperty();
        customerService.setType("click");
        customerService.setName("客服");
        customerService.setKey("customerService");
        SubButton learnerCenterSub = new SubButton();
        List<CommProperty> learnerCenterList = new ArrayList<>();
        learnerCenterList.add(learnerInfo);
        learnerCenterList.add(myTask);
        learnerCenterList.add(creExchange);
        learnerCenterList.add(customerService);
        learnerCenterSub.setName("学员中心");
        learnerCenterSub.setSub_button(learnerCenterList);

        //主按钮
        BaseButton baseButton = new BaseButton();
        List<SubButton> buttonList = new ArrayList<>();
        buttonList.add(aboutEasyLearnSub);
        buttonList.add(quickListenSub);
        buttonList.add(learnerCenterSub);
        baseButton.setButton(buttonList);

        String msgJson = gson.toJson(baseButton);
        logger.info(msgJson);

        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        HttpEntity<String> formEntity = new HttpEntity<String>(msgJson, headers);

        String respMsg = restTemplate.postForObject("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken,formEntity,String.class);
        logger.info(respMsg);
        return respMsg;
    }

    /**
     *
     * <pre>
     * 任务：
     * 描述： 微信网页jsjdk使用的签名
     * returnType：String
     */
    public  String getSignatureInfo(String noncestr, String jsapiTicket, String timestamp, String url) {
        // noncestr（随机字符串）
        // 有效的jsapi_ticket
        // timestamp（时间戳）
        // url（当前网页的URL，不包含#及其后面部分）
        String str = "jsapi_ticket=" + jsapiTicket + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url="
                + url;
        String sha1Signature = EncryptUtil.sha1Encode(str);

        return "{\"signature\":\"" + sha1Signature + "\",\"noncestr\":\"" + noncestr + "\",\"timestamp\":\"" + timestamp+ "\",\"appId\":\"" + accessTokenService.appId
                + "\"}";
    }

    /**
     * 获取jsapi ticket
     *
     * @return String
     */
    public String getJsTicket(){
        long now = new Date().getTime();
        if ((now - jsapi_getTime) / 1000 >= (jsapi_expires_in - 2)) {
            String accessToken = accessTokenService.getAccessToken();
            logger.info("accessToken:"+accessToken);
            String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + accessToken
                    + "&type=jsapi";
            String val = "";
            try {
                val = restTemplate.getForObject(url,String.class);
            } catch (Exception e) {
                logger.error("获取jsTicket失败"+e);
            }
            logger.info(val);
            if ((Double)gson.fromJson(val, Map.class).get("errcode")==0) {
                jsapi_ticket = (String)gson.fromJson(val,Map.class).get("ticket");
                jsapi_expires_in = (Double) gson.fromJson(val,Map.class).get("expires_in");
                jsapi_getTime = now;

            } else {
                jsapi_ticket = "";
            }
        }
        return jsapi_ticket;
    }

    /**
     *
     * <pre>
     * 任务：
     * 描述： 生成一个20位的随机字符串 并且生成一个时间戳
     * returnType：String
     * </pre>
     */
    public  String getNonceStr() {
        String str = "qwertyuioplkjhgfdsazxcvbnmMNBVCXZASDFGHJKLPOIUYTREWQ0123456789";
        Random rond = new Random();
        StringBuffer strbuf = new StringBuffer();

        for (int i = 0; i < 20; i++) {
            int number = rond.nextInt(str.length());
            strbuf.append(str.charAt(number));
        }
        nonce_str = strbuf.toString();
        timestamp = new Date().getTime() / 1000 + "";
        return nonce_str;
    }

    /**
     *返回时间戳
     */
    public  String getTimeStamp() {
        return timestamp;
    }



    /**
    * Author yuisama
    * @Date 2017/5/29 10:49
     * 新建文本类型消息
    */
    public static TextMessage createTextMessage(String openId,String toUserName,String content){
        TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(openId);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setFuncFlag(0);
        textMessage.setContent(content);
        return textMessage;
    }

    /**
    * Author yuisama
    * @Date 2017/5/29 11:00
     * 新建图文类型消息
    */
    public NewsMessage createNewsMessage(String openId,String toUserName,String picUrl,String title,String url,String description){
        NewsMessage newsMessage = new NewsMessage();
        newsMessage.setToUserName(openId);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(new Date().getTime());
        newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
        //Article
        Article article = new Article();
        article.setDescription(description);
        article.setPicUrl(picUrl);
        article.setTitle(title);
        article.setUrl(url);
        List<Article> list = new ArrayList<>();
        list.add(article);
        newsMessage.setArticleCount(list.size());
        newsMessage.setArticles(list);
        return newsMessage;
    }



    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param byteArray
     * @return String
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串
     *
     * @param mByte
     * @return String
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

}
