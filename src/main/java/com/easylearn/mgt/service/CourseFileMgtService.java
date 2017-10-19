package com.easylearn.mgt.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.mgt.dao.CourseFileMgtDao;
import com.easylearn.mgt.domain.CourseFileDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseFileMgtService extends MvcComponent{
    @Autowired
    private CourseFileMgtDao courseFileMgtDao ;

    /**
     * 根据partNum查询file信息
     */
    public String queryFileInfoByPartNum(int partNum) {
        List<CourseFileDomain> fileInfo = courseFileMgtDao.queryCourseFilesByPartNum(partNum) ;
        String fileInfoJson = gson.toJson(fileInfo) ;
        return fileInfoJson ;
    }

    /**
     * 批量向数据库插入file信息
     */
    public boolean addNewFiles(List<CourseFileDomain> addNewFilesPara) {
        for (CourseFileDomain courseFileDomain:addNewFilesPara){
            // 获取新增文件的信息
            int partNum = courseFileDomain.getPartNum() ;
            String fileType = courseFileDomain.getFileType() ;
            String filePath = courseFileDomain.getFilePath() ;
            String fileName = courseFileDomain.getFileName() ;
            String audioPath = courseFileDomain.getAudioPath() ;
            // 向数据库插入文件信息
            if (courseFileMgtDao.addNewFile(partNum,fileType,filePath,fileName,audioPath)){
                logger.info("Service层方法执行成功，已向数据库插入一条新的文件信息");
            }else {
                logger.error("插入文件信息失败，service层方法终止");
                return false ;
            }
        }
        return true ;
    }

    /**
     * 查询当前fileNum最大值
     */
    public int queryMaxFileNum(){
        return courseFileMgtDao.quertMaxFileNum();
    }
}
