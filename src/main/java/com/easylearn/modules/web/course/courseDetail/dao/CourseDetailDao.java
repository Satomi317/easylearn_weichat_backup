package com.easylearn.modules.web.course.courseDetail.dao;

import com.easylearn.modules.web.course.courseDetail.doamin.CourseFileDomain;
import com.easylearn.modules.web.course.courseDetail.doamin.CoursePartDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CZH on 2017/6/29.
 */
@Repository
public class CourseDetailDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<CoursePartDomain> getCoursePart(int chapterNum){
        Map paraMap = new HashMap();
        paraMap.put("CHAPTER_NUM", chapterNum);
        String querySql = "SELECT * FROM course_part WHERE CHAPTER_NUM=:CHAPTER_NUM ORDER BY PART_NUM";
        List<CoursePartDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(CoursePartDomain.class));
        return result;
    }

    public List<CourseFileDomain> getCourseFile(List<CoursePartDomain> parts){
        Map paraMap = new HashMap();
        paraMap.put("PART_NUM", parts.get(0).getPartNum());
        String querySql = "SELECT * FROM course_file WHERE PART_NUM = :PART_NUM";
        //拼接查询语句
        for(int i = 1; i < parts.size();i++){
            querySql += " OR PART_NUM = " + parts.get(i).getPartNum();
        }
        querySql += " ORDER BY FILE_NUM";

        List<CourseFileDomain> result =  namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(CourseFileDomain.class));
        return result;
    }
}
