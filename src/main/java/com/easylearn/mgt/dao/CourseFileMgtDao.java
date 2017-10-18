package com.easylearn.mgt.dao;

import com.easylearn.comm.MvcComponent;
import com.easylearn.mgt.domain.CourseFileDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CourseFileMgtDao extends MvcComponent{
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate ;

    /**
     * 根据partNum查询courseFile信息
     */
    public List<CourseFileDomain> queryCourseFilesByPartNum(int partNum){
        Map paraMap = new HashMap() ;
        paraMap.put("PART_NUM",partNum) ;
        String querySql = "SELECT * FROM course_file WHERE PART_NUM = :PART_NUM " ;
        List<CourseFileDomain> fileInfo = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(CourseFileDomain.class)) ;
        return fileInfo ;
    }

    /**
     * 根据partNum新增courseFile
     */
    public boolean addNewFile(int partNum, String fileType, String filePath, String fileName, String audioPath){
        logger.info("进入CourseFileDao层新增文件方法");
        Map paraMap = new HashMap() ;
        paraMap.put("PART_NUM",partNum) ;
        paraMap.put("FILE_TYPE",fileType) ;
        paraMap.put("FILE_PATH",filePath) ;
        paraMap.put("FILE_NAME",fileName) ;
        paraMap.put("AUDIO_PATH",audioPath) ;
        String addNewFileSql = "INSERT INTO course_file (PART_NUM, FILE_TYPE, FILE_PATH, FILE_NAME, AUDIO_PATH) VALUES (:PART_NUM, :FILE_TYPE, :FILE_PATH, :FILE_NAME, :AUDIO_PATH) " ;
        try{
            namedParameterJdbcTemplate.update(addNewFileSql,paraMap) ;
            logger.info("新增一条文件信息成功");
            return true ;
        }catch (Exception e){
            logger.error("DAO层新增文件信息失败");
            return false ;
        }
    }
}
