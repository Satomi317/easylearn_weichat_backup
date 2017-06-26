package com.easylearn.modules.openid.dao;

import com.easylearn.modules.accesstoken.domain.AccessTokenDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by CZH on 2017/6/16.
 */
@Repository
public class OpenIdDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void updateUserInfo(String openId,String nickname,String headImgUrl,String userToken) {
        Map paraMap = new HashMap();
        paraMap.put("OPENID",openId);
        paraMap.put("NICKNAME",nickname);
        paraMap.put("HEADIMG",headImgUrl);
        paraMap.put("ACCESS_TOKEN",userToken);
        String insertSql = "INSERT IGNORE INTO user_info (OPENID,NICKNAME,HEADIMG,ACCESS_TOKEN) VALUES (:OPENID,:NICKNAME,:HEADIMG,:ACCESS_TOKEN)";
        String updateSql = "UPDATE user_info SET ACCESS_TOKEN =:ACCESS_TOKEN WHERE OPENID=:OPENID";
        namedParameterJdbcTemplate.update(insertSql,paraMap);
        //每次更新用户的AccessToken
        namedParameterJdbcTemplate.update(updateSql,paraMap);
    }
}
