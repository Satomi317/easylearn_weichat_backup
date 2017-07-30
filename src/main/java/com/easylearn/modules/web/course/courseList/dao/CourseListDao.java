package com.easylearn.modules.web.course.courseList.dao;

import com.easylearn.modules.web.course.courseList.domain.CourseListDomain;
import com.easylearn.test.domain.UserCourseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CZH on 2017/6/25.
 */
@Repository
public class CourseListDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<CourseListDomain> getCourseList(int courseNum,int pushNumber){
        Map paraMap = new HashMap();
        paraMap.put("COURSE_NUM", courseNum);
        paraMap.put("PUSH_NUMBER",pushNumber);
        String querySql = "SELECT * FROM (SELECT * FROM course_chapter WHERE COURSE_NUM=:COURSE_NUM ORDER BY CHAPTER_NUM LIMIT 0,:PUSH_NUMBER)  AS a ORDER BY a.CHAPTER_NUM DESC;";
        List<CourseListDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(CourseListDomain.class));
        return result;
    }

    public List<CourseListDomain> getCourseListByChapterNum (int chapterNum){
        Map paraMap = new HashMap();
        paraMap.put("CHAPTER_NUM", chapterNum);
        String querySql = "SELECT * FROM course_chapter WHERE COURSE_NUM = (SELECT COURSE_NUM FROM course_chapter WHERE CHAPTER_NUM = :CHAPTER_NUM) ORDER BY CHAPTER_NUM";
        List<CourseListDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(CourseListDomain.class));
        return result;
    }

    /**
     * 获取用户某门课程的类型
     * @param openId
     * @param courseType
     * @return
     */
    public List<UserCourseDomain> getUserCourse(String openId, String courseType){
        Map paraMap = new HashMap();
        paraMap.put("OPENID", openId);
        paraMap.put("COURSE_TYPE", courseType);
        String querySql = "SELECT * FROM user_course WHERE OPENID=:OPENID AND COURSE_TYPE=:COURSE_TYPE";
        List<UserCourseDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(UserCourseDomain.class));
        return result;
    }
}
