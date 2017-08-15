package com.easylearn.modules.web.exchange.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.buy.dao.BuyCourseDao;
import com.easylearn.modules.web.exchange.dao.ExchangeDao;
import com.easylearn.modules.web.exchange.protocol.ProtocolIn;
import com.easylearn.modules.web.exchange.protocol.ProtocolOut;
import com.easylearn.modules.web.userInfo.dao.UserInfoDao;
import com.easylearn.modules.web.userInfo.domain.UserBonusDomain;
import com.easylearn.modules.web.userInfo.domain.UserCourseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CZH on 2017/6/27.
 */
@Service
public class ExchangeService extends MvcComponent {
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private ExchangeDao exchangeDao;

    @Autowired
    private BuyCourseDao buyCourseDao;

    public ProtocolOut doExchange(ProtocolIn protocolIn,String openId){
        ProtocolOut protocolOut = new ProtocolOut();

        int exchangeItem = protocolIn.getExchangeItem();
        long exchangeDay = protocolIn.getExchangeDay();

        //兑换前再次进行积分查询
        List<UserBonusDomain> bonusResult = userInfoDao.getUserBonus(openId);
        int userBonus = bonusResult.get(0).getBonus();

        if(userBonus < exchangeItem){
            protocolOut.setSuccess(false);
            protocolOut.setResult("抱歉，您的积分不足。");
            protocolOut.setMessage("error:积分不足");
        }else{

            //获取用户的全部购买课程
            List<UserCourseDomain> courseList = buyCourseDao.getUserAllCourse(openId);
            //如果用户未购买课程
            if(courseList.size() == 0){
                protocolOut.setSuccess(false);
                protocolOut.setResult("抱歉，您未购买任何课程！");
                protocolOut.setMessage("error:未购买任何课程");
            }
            //如果用户已购买，但已过期
            else{
                long curTime = System.currentTimeMillis();
                long addTime = exchangeDay * 24 * 60 * 60 *1000;
                for(UserCourseDomain item : courseList){
                    logger.info("");
                    //课程未过期，只更新expiryTime
                    if(item.getExpiryTime() > curTime){
                        long expiryTime = item.getExpiryTime() + addTime;
                        buyCourseDao.updateBuyCourse(openId,item.getCourseType(),Long.toString(item.getStartTime()),Long.toString(expiryTime));
                    }else{
                        //课程已过期，更新startTime和expiryTime
                        long expiryTime = curTime + addTime;
                        buyCourseDao.updateBuyCourse(openId,item.getCourseType(),Long.toString(curTime),Long.toString(expiryTime));
                    }
                }
                //扣除积分，增加课程日期
                exchangeDao.updateBonus((userBonus-exchangeItem),openId);

                protocolOut.setSuccess(true);
                protocolOut.setResult("积分兑换成功！");
                protocolOut.setMessage("success");
            }
        }
        return protocolOut;
    }
}
