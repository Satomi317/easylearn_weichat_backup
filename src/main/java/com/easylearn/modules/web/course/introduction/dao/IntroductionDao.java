package com.easylearn.modules.web.course.introduction.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.easylearn.modules.web.course.introduction.domain.CourseIntroductionDomain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CZH on 2017/6/19.
 */
@Repository
public class IntroductionDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 获取全部课程介绍
     * @return
     */
    public List<CourseIntroductionDomain> getAllCourseIntroduction(){
        String querySql = "SELECT * FROM course_introduction ORDER BY DATE DESC";
        Map paraMap = new HashMap();
        List<CourseIntroductionDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(CourseIntroductionDomain.class));
        return  result;
    }

    /**
     * 获取指定课程介绍
     * @return
     */
    public CourseIntroductionDomain getCourseIntroduction(String courseNum){
        Map paraMap = new HashMap();
        paraMap.put("COURSE_NUM", courseNum);
        String querySql = "SELECT * FROM course_introduction WHERE COURSE_NUM=:COURSE_NUM";

        CourseIntroductionDomain result = namedParameterJdbcTemplate.queryForObject(querySql,paraMap,new BeanPropertyRowMapper<>(CourseIntroductionDomain.class));
        return  result;
    }

    /**
     * 根据chapterNum获取指定课程介绍
     * @return
     */
    public CourseIntroductionDomain getCourseIntroductionByChapter(String chapterNum){
        Map paraMap = new HashMap();
        paraMap.put("CHAPTER_NUM", chapterNum);
        String querySql = "SELECT * FROM course_introduction WHERE COURSE_NUM=(SELECT COURSE_NUM FROM course_chapter WHERE CHAPTER_NUM = :CHAPTER_NUM)";

        CourseIntroductionDomain result = namedParameterJdbcTemplate.queryForObject(querySql,paraMap,new BeanPropertyRowMapper<>(CourseIntroductionDomain.class));
        return  result;
    }
}
