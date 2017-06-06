package com.easylearn.modules.accesstoken.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.accesstoken.dao.AccessTokenDao;
import com.easylearn.modules.accesstoken.domain.AccessTokenDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;


/**
 * Created by yuisama on 2017/5/12.
 */
@Service
public class AccessTokenService extends MvcComponent{
    @Autowired
    private AccessTokenDao accessTokenDao;



    @Value("${appId}")
    private String appId;

    private static final long EXPIRES_IN = 7200L;

    public String getAccessToken(){
        AccessTokenDomain accessTokenDomain;
        String accessToken = "";
        try{
            accessTokenDomain = accessTokenDao.findTokenByappId(appId);
            String appSecret = accessTokenDomain.getAppSecret();
            long now = System.currentTimeMillis();
            if ((now-accessTokenDomain.getGetTime())/1000 >=(EXPIRES_IN-2)){
                logger.info("AccessToken过期，现在调用微信接口重新获取Token");
                String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                        + appId + "&secret=" + appSecret;
                String resp = restTemplate.getForObject(url,String .class);
                accessToken = (String)gson.fromJson(resp, Map.class).get("access_token");
                //更新数据库中Token信息
                accessTokenDao.updateToken(appId,accessToken,now);
                logger.info("从微信返回的数据为:"+resp);
                return accessToken;

            }
            else {
                logger.info("数据库中Token有效，返回给用户");
                return accessTokenDomain.getAccessToken();
            }
        }
        catch (Exception e){
            logger.error("获取Token失败");
            return null;
        }

    }
}
