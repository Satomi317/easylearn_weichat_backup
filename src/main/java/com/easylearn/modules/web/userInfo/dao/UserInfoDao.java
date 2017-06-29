package com.easylearn.modules.web.userInfo.dao;

import com.easylearn.modules.web.userInfo.domain.UserBonusDomain;
import com.easylearn.modules.web.userInfo.domain.UserCourseDomain;
import com.easylearn.modules.web.userInfo.domain.UserInfoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by CZH on 2017/6/26.
 */
@Repository
public class UserInfoDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<UserBonusDomain> getUserBonus(String openId){
        String sqlStr = "SELECT * FROM user_bonus WHERE OPENID=:OPENID";
        Map paraMap = new HashMap();
        paraMap.put("OPENID", openId);
        List<UserBonusDomain> result = namedParameterJdbcTemplate.query(sqlStr,paraMap,new BeanPropertyRowMapper<>(UserBonusDomain.class));
        return result;
    }

    public List<UserCourseDomain> getUserCourse(String openId){
        String sqlStr = "SELECT user_course.*,course_type.COURSE_NAME FROM user_course JOIN course_type ON user_course.COURSE_TYPE = course_type.COURSE_TYPE WHERE user_course.OPENID = :OPENID";
        Map paraMap = new HashMap();
        paraMap.put("OPENID", openId);
        List<UserCourseDomain> result = namedParameterJdbcTemplate.query(sqlStr,paraMap,new BeanPropertyRowMapper<>(UserCourseDomain.class));
        return result;
    }

    public UserInfoDomain getUserInfo(String openId){
        String sqlStr = "SELECT * FROM user_info WHERE OPENID=:OPENID";
        Map paraMap = new HashMap();
        paraMap.put("OPENID", openId);
        UserInfoDomain result = namedParameterJdbcTemplate.queryForObject(sqlStr,paraMap,new BeanPropertyRowMapper<>(UserInfoDomain.class));
        return result;
    }

    public void addUserBonus(String openId){
        Map paraMap = new HashMap();
        paraMap.put("OPENID",openId);
        String insertSql = "INSERT INTO user_bonus (OPENID) VALUES (:OPENID)";
        namedParameterJdbcTemplate.update(insertSql,paraMap);
    }
}
