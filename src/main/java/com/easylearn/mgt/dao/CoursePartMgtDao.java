package com.easylearn.mgt.dao;

import com.easylearn.comm.MvcComponent;
import com.easylearn.mgt.domain.CoursePartDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CoursePartMgtDao extends MvcComponent{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;
    @Autowired
    private JdbcTemplate jdbcTemplate ;

    /**
     * 根据chapterNum查询coursePart信息
     */
    public List<CoursePartDomain> queryPartInfoByChapterNum(int chapterNum){
        Map paraMap = new HashMap<>() ;
        paraMap.put("CHAPTER_NUM",chapterNum) ;
        String queryPartSql = "SELECT * FROM course_part WHERE CHAPTER_NUM =:CHAPTER_NUM" ;
        List<CoursePartDomain> partInfoByChapterNum = namedParameterJdbcTemplate.query(queryPartSql,paraMap,new BeanPropertyRowMapper<>(CoursePartDomain.class)) ;
        return partInfoByChapterNum;
    }

    /**
     * 根据chapterNum新增coursePart
     */
    public boolean addNewParts(String partTitle, int chapterNum){
        logger.info("进入addNewPartDao层方法");
        Map paraMap = new HashMap();
        paraMap.put("PART_TITLE",partTitle) ;
        paraMap.put("CHAPTER_NUM",chapterNum) ;
        String addNewPartSql = "INSERT INTO course_part (PART_TITLE, CHAPTER_NUM) VALUES (:PART_TITLE,:CHAPTER_NUM)" ;
        try{
            namedParameterJdbcTemplate.update(addNewPartSql,paraMap) ;
            logger.info("addNewPartDao层方法执行成功，返回success");
            return true ;
        }catch (Exception e){
            logger.error("addNewPartDao层方法执行失败,请检查partNum是否重复，返回false"+e);
            return false;
        }
    }

    /**
     * 查询当前partNum最大值
     */
    public int quertMaxPartNum(){
        logger.info("查询partNum最大值");
        String queryMaxPartNumSql = "SELECT MAX(PART_NUM) FROM course_part" ;
        int maxPartDomain = jdbcTemplate.queryForObject(queryMaxPartNumSql,Integer.class);
        return maxPartDomain ;
    }
}
