package com.easylearn.modules.web.introduction.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import com.easylearn.modules.web.introduction.domain.CourseIntroductionDomain;

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
    public List<CourseIntroductionDomain> getCourseIntroduction(){
        String querySql = "SELECT * FROM course_introduction ORDER BY DATE DESC";
        Map paraMap = new HashMap();
        List<CourseIntroductionDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(CourseIntroductionDomain.class));
        return  result;
    }
}
