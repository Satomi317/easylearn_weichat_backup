package com.easylearn.mgt.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.mgt.dao.CoursePartMgtDao;
import com.easylearn.mgt.domain.CoursePartDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursePartMgtService extends MvcComponent {
    @Autowired
    private CoursePartMgtDao coursePartMgtDao ;

    /**
     * 查询当前partNum最大值
     */
    public int queryMaxPartNum(){
        return coursePartMgtDao.quertMaxPartNum();
    }

    /**
     * 根据chapterNum查询part信息
     */
    public String quertPartInfoByChapterNum(int chapterNum){
        List<CoursePartDomain> partInfoByChapterNum = coursePartMgtDao.queryPartInfoByChapterNum(chapterNum) ;
        String partInfo2json = gson.toJson(partInfoByChapterNum) ;
        return partInfo2json ;
    }

    /**
     * 根据传入List向数据库新增part
     */
    public boolean addNewParts(List<CoursePartDomain> addNewPartsParm){
        for (CoursePartDomain coursePartDomain : addNewPartsParm){
            //获取要插入数据库的信息
            String partTitle = coursePartDomain.getPartTitle() ;
            int chapterNum = coursePartDomain.getChapterNum() ;
            //向数据库插入part信息
            if (coursePartMgtDao.addNewParts(partTitle,chapterNum)){
                logger.info("已向数据库插入一条新的part记录");
            }else {
                logger.error("插入数据失败,service方法终止");
                return false ;
            }
        }
        return true ;
    }
}
