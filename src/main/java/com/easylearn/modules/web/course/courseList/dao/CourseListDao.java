package com.easylearn.modules.web.course.courseList.dao;

import com.easylearn.modules.web.course.courseList.domain.CourseListDomain;
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

    public List<CourseListDomain> getCourseList(int courseNum){
        Map paraMap = new HashMap();
        paraMap.put("COURSE_NUM", courseNum);
        String querySql = "SELECT * FROM course_chapter WHERE COURSE_NUM=:COURSE_NUM ORDER BY CHAPTER_NUM";
//        List<CourseListDomain> result = namedParameterJdbcTemplate.queryForList(querySql,paraMap);
        List<CourseListDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(CourseListDomain.class));
        return result;
    }
}
