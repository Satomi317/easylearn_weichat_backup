package com.easylearn.modules.web.homework.dao;

import com.easylearn.modules.web.homework.domain.HomeworkDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CZH on 2017/6/30.
 */
@Repository
public class HomeworkDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void insertHomework(String localId,String openId,String timetamp,long chapterNum){
        String insertSql = "INSERT IGNORE INTO user_homework (LOCALID,OPENID,TIMETAMP,CHAPTERNUM) VALUES (:LOCALID,:OPENID,:TIMETAMP,:CHAPTERNUM)";
        Map paraMap = new HashMap();
        paraMap.put("LOCALID",localId);
        paraMap.put("OPENID",openId);
        paraMap.put("CHAPTERNUM",chapterNum);
        paraMap.put("TIMETAMP",timetamp);
        namedParameterJdbcTemplate.update(insertSql,paraMap);
    }

    public List<HomeworkDomain> getUserHomework(String openId){
        Map paraMap = new HashMap();
        paraMap.put("OPENID",openId);
        String querySql =   "SELECT user_homework.*,course_chapter.CHAPTER_TITLE " +
                            "FROM user_homework " +
                            "JOIN course_chapter " +
                            "ON user_homework.CHAPTERNUM = course_chapter.CHAPTER_NUM " +
                            "WHERE OPENID=:OPENID ORDER BY TIMETAMP DESC ";

        List<HomeworkDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(HomeworkDomain.class));
        return result;
    }

    public List<HomeworkDomain> getUserRecord(String openId,long chapterNum){
        Map paraMap = new HashMap();
        paraMap.put("OPENID",openId);
        paraMap.put("CHAPTERNUM",chapterNum);
        String querySql = "SELECT * FROM user_homework WHERE OPENID=:OPENID AND CHAPTERNUM=:CHAPTERNUM";
        List<HomeworkDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(HomeworkDomain.class));
        return result;
    }
}
