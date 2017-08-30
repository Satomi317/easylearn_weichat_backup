package com.easylearn.modules.web.exchange.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.easylearn.modules.web.exchange.domain.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CZH on 2017/6/27.
 */
@Repository
public class ExchangeDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 更新兑换后的用户积分
     * @param newBonus
     * @param openId
     */
    public void updateBonus(int newBonus,String openId){
        Map paraMap = new HashMap();
        paraMap.put("OPENID",openId);
        paraMap.put("BONUS",newBonus);
        String updateSql = "UPDATE user_bonus SET BONUS =:BONUS WHERE OPENID=:OPENID";
        namedParameterJdbcTemplate.update(updateSql,paraMap);
    }

    /**
     * 更新用户课程有效期
     * @param day
     * @param openId
     */
//    public void updateExpiryTime(int day,String openId){
//
//    }

    /**
     * 获取用户当前积分信息
     * @param openId
     * @return
     */
    public List<UserBonusDomain> getUserBonus(String openId){
        Map paraMap = new HashMap();
        paraMap.put("OPENID",openId);
        String querySql = "SELECT * FROM user_bonus WHERE OPENID=:OPENID";
        List<UserBonusDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(UserBonusDomain.class));
        return result;
    }

    /**
     * 更新邀请者的积分
     * @param openId
     */
    public void updateInviterBonus(String openId, String myOpenId){
        Map paraMap = new HashMap();
        paraMap.put("OPENID",openId);
        paraMap.put("MYOPENID",myOpenId);
        String updateSql = "UPDATE user_bonus SET BONUS=BONUS + 1000 WHERE OPENID=:OPENID OR OPENID=:MYOPENID";
        namedParameterJdbcTemplate.update(updateSql,paraMap);
    }
}
