package com.easylearn.modules.web.homework.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.exchange.domain.UserBonusDomain;
import com.easylearn.modules.web.homework.dao.HomeworkDao;
import com.easylearn.modules.web.homework.domain.HomeworkDomain;
import com.easylearn.modules.web.exchange.dao.ExchangeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CZH on 2017/6/30.
 */
@Service
public class HomeworkService extends MvcComponent {
    @Autowired
    private HomeworkDao homeworkDao;

    @Autowired
    private ExchangeDao exchangeDao;

    public String insertHomework(String localId,String openId,String timetamp,long chapterNum){
        List<HomeworkDomain> result = homeworkDao.getUserRecord(openId,chapterNum);
        //如果是该课程的第一次作业
        if(result.size() == 0){
            List<UserBonusDomain> userBonusList = exchangeDao.getUserBonus(openId);
            if(userBonusList.size()>0){
                UserBonusDomain userBonus = userBonusList.get(0);
                //提交作业加十分
                int bonus = userBonus.getBonus() + 10;
                //更新积分
                exchangeDao.updateBonus(bonus,openId);
            }else{
                logger.error("没有用户的积分记录，openId：" + openId);
            }
        }
        //添加作业记录
        homeworkDao.insertHomework(localId,openId,timetamp,chapterNum);

        return "";
    }

    public String getUserHomework(String openId){
        List<HomeworkDomain> result = homeworkDao.getUserHomework(openId);
        return gson.toJson(result);
    }
}
