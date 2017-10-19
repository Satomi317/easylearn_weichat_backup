package com.easylearn.mgt.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.mgt.dao.CourseChapterMgtDao;
import com.easylearn.mgt.domain.CourseChapterDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseChapterMgtService extends MvcComponent{
    @Autowired
    private CourseChapterMgtDao courseChapterMgtDao ;

    /**
     * 查询数据库所有章节
     */
    public String queryAllChapterInfo(){
        logger.info("查询数据库所有章节开始..");
        List<CourseChapterDomain> courseInfo = courseChapterMgtDao.findAllChapter();
        String courseInfo2Json = gson.toJson(courseInfo) ;
        logger.info("查询完毕'");
        return courseInfo2Json ;
    }
    /**
     * 新增章节
     */
    public void addNewChapter(String chapterTitle, int courseNum, String courseImg){
        logger.info("新增章节开始..") ;
        courseChapterMgtDao.addNewChapter(chapterTitle,courseNum,courseImg) ;
    }

    /**
     * 查询当前fileNum最大值
     */
    public int queryMaxChapterNum(){
        return courseChapterMgtDao.quertMaxChapterNum();
    }
}
