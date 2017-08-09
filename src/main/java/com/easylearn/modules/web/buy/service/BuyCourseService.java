package com.easylearn.modules.web.buy.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.buy.dao.BuyCourseDao;
import com.easylearn.modules.web.buy.domain.CourseTypeDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by czh on 2017/8/2.
 */
@Service
public class BuyCourseService extends MvcComponent {

    @Autowired
    private BuyCourseDao buyCourseDao;

    public Boolean buyCourse(String openId, String buyId){

        List<CourseTypeDomain> courseInfo =  buyCourseDao.getBuyCourseDetailById(buyId);

        if(courseInfo.size() == 0){
            logger.error("未查找到该课程的在售信息。");
            return false;
        }else{

            String courseType = courseInfo.get(0).getCourseType();
            int courseDays = courseInfo.get(0).getCourseDays();

            long startTime = System.currentTimeMillis();
            long expiryTime = startTime + courseDays * 24 * 60 * 60 *1000;
            try{
                buyCourseDao.addBuyCourse(openId,courseType,Long.toString(startTime),Long.toString(expiryTime));
            }catch (Exception e){
                logger.error("购买记录添加失败！");
                return false;
            }
            return  true;
        }
    }

    /**
     * 获取所有在售课程介绍
     * @param courseType
     * @return
     */
    public String getAllSellCourse(String courseType){
        return gson.toJson(buyCourseDao.getAllBuyCourseDetail(courseType));
    }
}
