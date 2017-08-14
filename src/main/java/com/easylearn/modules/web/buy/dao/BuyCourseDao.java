package com.easylearn.modules.web.buy.dao;

import com.easylearn.modules.web.buy.domain.CourseTypeDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.easylearn.modules.web.userInfo.domain.UserCourseDomain;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by czh on 2017/8/2.
 */
@Repository
public class BuyCourseDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 插入用户购买记录
     * @param openId
     * @param courseType
     * @param startTime
     * @param expiryTime
     */
    public void addBuyCourse(String openId,String courseType,String startTime,String expiryTime){
        Map paraMap = new HashMap();
        paraMap.put("OPENID",openId);
        paraMap.put("COURSE_TYPE",courseType);
        paraMap.put("START_TIME",startTime);
        paraMap.put("EXPIRY_TIME",expiryTime);
        String insertSql = "INSERT INTO user_course (OPENID,COURSE_TYPE,START_TIME,EXPIRY_TIME,PUSH_NUMBER) VALUES (:OPENID,:COURSE_TYPE,:START_TIME,:EXPIRY_TIME,0)";
        namedParameterJdbcTemplate.update(insertSql,paraMap);
    }

    public void updateBuyCourse(String openId,String courseType,String startTime,String expiryTime){
        Map paraMap = new HashMap();
        paraMap.put("COURSE_TYPE",courseType);
        paraMap.put("OPENID",openId);
        paraMap.put("START_TIME",startTime);
        paraMap.put("EXPIRY_TIME",expiryTime);
        String updateSql = "UPDATE user_course SET START_TIME=:START_TIME,EXPIRY_TIME=:EXPIRY_TIME WHERE OPENID=:OPENID AND COURSE_TYPE=:COURSE_TYPE";
        namedParameterJdbcTemplate.update(updateSql,paraMap);
    }

    /**
     * 根据ID获取要购买课程的详细信息
     * @param courseId
     * @return
     */
    public List<CourseTypeDomain> getBuyCourseDetailById(String courseId){
        Map paraMap = new HashMap();
        paraMap.put("ID",courseId);
        String querySql = "SELECT * FROM course_type WHERE ID=:ID";
        List<CourseTypeDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(CourseTypeDomain.class));
        return result;
    }

    /**
     * 查找零/微基础全部在售课程
     * @param courseType
     * @return
     */
    public List<CourseTypeDomain> getAllBuyCourseDetail(String courseType){
        Map paraMap = new HashMap();
        paraMap.put("COURSE_TYPE",courseType);
        String querySql = "SELECT * FROM course_type WHERE COURSE_TYPE=:COURSE_TYPE";
        List<CourseTypeDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(CourseTypeDomain.class));
        return result;
    }

    /**
     * 获取用户已购买的某类型课程
     * @param openId
     * @param courseType
     * @return
     */
    public List<UserCourseDomain> getUserCourseByType(String openId, String courseType){
        Map paraMap = new HashMap();
        paraMap.put("COURSE_TYPE",courseType);
        paraMap.put("OPENID",openId);
        String querySql = "SELECT * FROM user_course WHERE COURSE_TYPE=:COURSE_TYPE AND OPENID=:OPENID";
        List<UserCourseDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(UserCourseDomain.class));
        return result;
    }
//
//    public List<UserCourseDomain> getUserCourseById(String openId, String courseId){
//        Map paraMap = new HashMap();
//        paraMap.put("ID",courseId);
//        paraMap.put("OPENID",openId);
//        String querySql = "SELECT * FROM user_course WHERE COURSE_TYPE=(SELECT course_type.COURSE_TYPE FROM course_type WHERE ID=:ID) AND OPENID=:OPENID";
//        List<UserCourseDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(UserCourseDomain.class));
//        return result;
//    }
}
