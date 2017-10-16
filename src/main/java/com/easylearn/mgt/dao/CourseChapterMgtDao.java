package com.easylearn.mgt.dao;

import com.easylearn.mgt.domain.CourseChapterDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseChapterMgtDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 查找当前数据库所有章节信息
     */
    public List<CourseChapterDomain> findAllChapter() {
        String findChapterSql = "SELECT * FROM course_chapter ";
        List<CourseChapterDomain> result = namedParameterJdbcTemplate.query(findChapterSql, new BeanPropertyRowMapper<>(CourseChapterDomain.class));
        return result;
    }

    /**
     * 新增章节
     */
    public void addNewChapter(int chapterNum, String chapterTitle, int courseNum, String courseImg) {
        Map paraMap = new HashMap();
        paraMap.put("CHAPTER_NUM", chapterNum);
        paraMap.put("CHAPTER_TITLE", chapterTitle);
        paraMap.put("COURSE_NUM", courseNum);
        paraMap.put("COURSE_IMG", courseImg);
        String addNewCourseSql = "INSERT course_chapter (CHAPTER_NUM,CHAPTER_TITLE,COURSE_NUM,COURSE_IMG) VALUES (:CHAPTER_NUM,:CHAPTER_TITLE,:COURSE_NUM,:COURSE_IMG)";
        namedParameterJdbcTemplate.update(addNewCourseSql,paraMap) ;
    }

}
