package com.easylearn.mgt.dao;

import com.easylearn.comm.MvcComponent;
import com.easylearn.mgt.domain.CourseChapterDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseChapterMgtDao  extends MvcComponent {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;
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
    public void addNewChapter(String chapterTitle, int courseNum, String courseImg) {
        Map paraMap = new HashMap();
        paraMap.put("CHAPTER_TITLE", chapterTitle);
        paraMap.put("COURSE_NUM", courseNum);
        paraMap.put("COURSE_IMG", courseImg);
        String addNewCourseSql = "INSERT course_chapter (CHAPTER_TITLE,COURSE_NUM,COURSE_IMG) VALUES (:CHAPTER_TITLE,:COURSE_NUM,:COURSE_IMG)";
        namedParameterJdbcTemplate.update(addNewCourseSql,paraMap) ;
    }

    /**
     * 查询当前fileNum最大值
     */
    public int quertMaxChapterNum(){
        logger.info("查询ChapterNum最大值");
        String queryMaxChapterNumSql = "SELECT COUNT(CHAPTER_NUM) FROM course_chapter";
        int maxChapterDomain = jdbcTemplate.queryForObject(queryMaxChapterNumSql,Integer.class);
        return maxChapterDomain ;
    }
}
