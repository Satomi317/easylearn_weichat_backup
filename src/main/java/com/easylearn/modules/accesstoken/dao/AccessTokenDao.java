package com.easylearn.modules.accesstoken.dao;

import com.easylearn.modules.accesstoken.domain.AccessTokenDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yuisama on 2017/5/12.
 */
@Repository
public class AccessTokenDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public AccessTokenDomain findTokenByappId(String appId) {
        Map paraMap = new HashMap();
        paraMap.put("APP_ID", appId);
        String querySql = "SELECT APP_SECRET, ACCESS_TOKEN,GET_TIME FROM WEICHAT_TOKEN where APP_ID=:APP_ID";
        AccessTokenDomain accessTokenDomain = namedParameterJdbcTemplate.queryForObject(querySql, paraMap, new BeanPropertyRowMapper<>(AccessTokenDomain.class));
        return accessTokenDomain;
    }

    public void updateToken(String appId,String accessToken,long getTime){
        Map paraMap = new HashMap();
        paraMap.put("APP_ID",appId);
        paraMap.put("ACCESS_TOKEN",accessToken);
        paraMap.put("GET_TIME",getTime);
        String updateSql = "UPDATE WEICHAT_TOKEN  SET ACCESS_TOKEN=:ACCESS_TOKEN ,GET_TIME=:GET_TIME where APP_ID=:APP_ID";
        namedParameterJdbcTemplate.update(updateSql,paraMap);
    }
}
