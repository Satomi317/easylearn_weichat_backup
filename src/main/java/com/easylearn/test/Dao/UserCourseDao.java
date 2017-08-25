package com.easylearn.test.Dao;

import com.easylearn.test.domain.UserCourseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by czh on 2017/7/29.
 */
@Repository
public class UserCourseDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 查找所有课程在有效期内的零基础用户
     * @return
     */
    public List<UserCourseDomain> getAllZeroBaseUser(String curTime){
        String querySql = "SELECT * FROM user_course WHERE COURSE_TYPE = 0 AND EXPIRY_TIME > :CUR_TIME";
        Map paraMap = new HashMap();
        paraMap.put("CUR_TIME", curTime);
        List<UserCourseDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(UserCourseDomain.class));
        return result;
    }

    /**
     * 查找所有课程在有效期内的微基础用户
     * @return
     */
    public List<UserCourseDomain> getAllTinyBaseUser(String curTime){
        String querySql = "SELECT * FROM user_course WHERE COURSE_TYPE = 1 AND EXPIRY_TIME > :CUR_TIME";
        Map paraMap = new HashMap();
        paraMap.put("CUR_TIME", curTime);
        List<UserCourseDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(UserCourseDomain.class));
        return result;
    }

    /**
     * 更新用户的推送记录
     * @param openId
     * @param CourseType
     * @param curTime
     */
    public void updatePushNumber(String openId, String CourseType, String curTime){
        String updateSql = "update user_course set PUSH_NUMBER = PUSH_NUMBER + 1 WHERE OPENID = :OPEN_ID AND COURSE_TYPE = :COURSE_TYPE AND EXPIRY_TIME > :CUR_TIME";
        Map paraMap = new HashMap();
        paraMap.put("OPEN_ID", openId);
        paraMap.put("COURSE_TYPE", CourseType);
        paraMap.put("CUR_TIME", curTime);
        namedParameterJdbcTemplate.update(updateSql,paraMap);
    }

    /**
     * 获取所有需要推送试听课的用户
     * @return
     */
    public List<UserCourseDomain> getAllDemoUser(){
        String querySql = "SELECT * FROM user_course WHERE COURSE_TYPE = 4 AND PUSH_NUMBER < 6";
        Map paraMap = new HashMap();
        List<UserCourseDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(UserCourseDomain.class));
        return result;
    }

    /**
     * 更新用户试听课的推送记录
     *
     */
    public void updateDemoPushNumber(String openId,int addNum){
        String updateSql = "update user_course set PUSH_NUMBER = PUSH_NUMBER + :ADD_NUM WHERE OPENID = :OPEN_ID AND COURSE_TYPE = \"4\"";
        Map paraMap = new HashMap();
        paraMap.put("OPEN_ID", openId);
        paraMap.put("ADD_NUM", addNum);
        namedParameterJdbcTemplate.update(updateSql,paraMap);
    }
}
