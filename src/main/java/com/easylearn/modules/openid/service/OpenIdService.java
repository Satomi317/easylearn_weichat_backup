package com.easylearn.modules.openid.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.accesstoken.dao.AccessTokenDao;
import com.google.gson.JsonSyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.Map;

/**
 * Created by yuisama on 2017/6/12.
 */
@Service
public class OpenIdService extends MvcComponent {
    @Autowired
    private AccessTokenDao accessTokenDao;

    @Value("${appId}")
    public String appId;

    public String getOpenId(String code){
        String appSecret = accessTokenDao.findTokenByappId(appId).getAppSecret();
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appSecret
                + "&code=" + code + "&grant_type=authorization_code";
        String openId = "";

        try {
            String resp = restTemplate.getForObject(url,String.class);
            openId = (String)gson.fromJson(resp, Map.class).get("openid");
        } catch (RestClientException e) {
           logger.error("发送请求获取openId失败"+e);
        } catch (JsonSyntaxException e) {
            logger.error("Json转换出错"+e);
        }
        return openId;
    }
}
