package com.easylearn.test.Dao;

import com.easylearn.test.domain.CourseChapterDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by czh on 2017/7/29.
 */
@Repository
public class CourseChapterDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 查找要推送的课程信息（chpterNum）
     * @param pushNumber  推送次数
     * @param courseNum   推送的课程编号
     * @return
     */
    public List<CourseChapterDomain> getPushCourse(int pushNumber,int courseNum){
        String querySql = "SELECT * FROM  (SELECT * FROM course_chapter WHERE COURSE_NUM=:COURSE_NUMBER order by CHAPTER_NUM ) as a limit :PUSH_NUMBER,1";
        Map paraMap = new HashMap();
        paraMap.put("PUSH_NUMBER", pushNumber);
        paraMap.put("COURSE_NUMBER", courseNum);
        List<CourseChapterDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(CourseChapterDomain.class));
        return result;
    }

    /**
     * 关注公众号时，为用户添加试听课推送记录
     * @param openId
     */
    public void addDemoPush(String openId){
        Map paraMap = new HashMap();
        paraMap.put("OPENID", openId);
        String insertSql = "INSERT IGNORE INTO user_course (OPENID,COURSE_TYPE,START_TIME,EXPIRY_TIME,PUSH_NUMBER) VALUES (:OPENID,4,0,0,0)";
        namedParameterJdbcTemplate.update(insertSql,paraMap);
    }


//    public List<CourseChapterDomain> getDemoPushCourse(String pushNumber){
//        String querySql = ""
//    }
}
