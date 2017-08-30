package com.easylearn.modules.web.buy.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.buy.dao.BuyCourseDao;
import com.easylearn.modules.web.buy.domain.CourseTypeDomain;
import com.easylearn.modules.web.exchange.domain.UserBonusDomain;
import com.easylearn.modules.web.userInfo.domain.UserCourseDomain;
import com.easylearn.modules.web.userInfo.domain.UserInfoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easylearn.modules.web.exchange.dao.ExchangeDao;
import com.easylearn.modules.web.userInfo.dao.UserInfoDao;
import java.util.List;

/**
 * Created by czh on 2017/8/2.
 */
@Service
public class BuyCourseService extends MvcComponent {

    @Autowired
    private BuyCourseDao buyCourseDao;
    @Autowired
    private ExchangeDao exchangeDao;
    @Autowired
    private UserInfoDao userInfoDao;

    public Boolean buyCourse(String openId, String buyId){

        List<CourseTypeDomain> courseInfo =  buyCourseDao.getBuyCourseDetailById(buyId);

        if(courseInfo.size() == 0){
            logger.error("未查找到该课程的在售信息。");
            return false;
        }else{

            String courseType = courseInfo.get(0).getCourseType();
            long courseDays = courseInfo.get(0).getCourseDays();

            long startTime = System.currentTimeMillis();
            long addTime = courseDays * 24 * 60 * 60 *1000;
            long expiryTime = startTime + addTime;

            try{
                //先查询用户之前是否购买过课程
                List<UserCourseDomain> res = buyCourseDao.getUserCourseByType(openId,courseType);
                //未购买过课程
                if(res.size() == 0){
                    //插入购买信息
                    buyCourseDao.addBuyCourse(openId,courseType,Long.toString(startTime),Long.toString(expiryTime));
                    //停止向用户推送试听课
                    buyCourseDao.updateDemoCourse(openId);
                }else{
                    //更新购买信息，只更新购买时间和有效期，不改变推送次数
                    buyCourseDao.updateBuyCourse(openId,courseType,Long.toString(startTime),Long.toString(expiryTime));
                }

            }catch (Exception e){
                logger.error("购买记录添加失败，数据库操作异常！");
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

    /**
     * 判断用户是否购买过课程
     * @param openId
     * @param courseType
     * @return
     */
    public Boolean hasBuy(String openId, String courseType){
        logger.info("courseType="+courseType);
        List<UserCourseDomain> res = buyCourseDao.getUserCourseByType(openId,courseType);
        //false代表未购买
        if(res.size() == 0){
            return false;
        }else{
            UserCourseDomain course = res.get(0);
            long curTime = System.currentTimeMillis();
            //如果购买的课程已过期，可再次购买。
            if(curTime > course.getExpiryTime()){
                return false;
            }else{
                //已购买且未过期
                return true;
            }

        }
    }


    public void inviteSuccess(String openId, String inviteCode){
        logger.info(inviteCode);
        List<UserInfoDomain> result = userInfoDao.getUserInfoByMemberId(inviteCode);
        logger.info(result);
        if(result.size()>0){
            UserInfoDomain inviter = result.get(0);
            String inviterOpenId = inviter.getOpenid();
            logger.info("inviterOpenId="+inviterOpenId);
            //邀请者openId不能是是购课者自己
            if(inviterOpenId != openId){
                exchangeDao.updateInviterBonus(inviterOpenId,openId);
            }else{
                logger.error("邀请码对应用户不能为自己");
            }
        }else{
            logger.error("邀请码对应用户不存在");
        }
    }
}
