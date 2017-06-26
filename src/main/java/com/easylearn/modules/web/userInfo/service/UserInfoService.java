package com.easylearn.modules.web.userInfo.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.userInfo.dao.UserInfoDao;
import com.easylearn.modules.web.userInfo.domain.UserBonusDomain;
import com.easylearn.modules.web.userInfo.domain.UserCourseDomain;
import com.easylearn.modules.web.userInfo.domain.UserInfoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CZH on 2017/6/26.
 */
@Service
public class UserInfoService extends MvcComponent {
    @Autowired
    private UserInfoDao userInfoDao;

    /**
     * 获取用户的积分信息
     * @param openId
     * @return
     */
    public String getUserBonus(String openId){
        UserBonusDomain result = userInfoDao.getUserBonus(openId);
        return gson.toJson(result);
    }

    /**
     * 获取用户的已买课程信息
     * @param openId
     * @return
     */
    public String getUserCourse(String openId){
        List<UserCourseDomain> result = userInfoDao.getUserCourse(openId);
        return gson.toJson(result);
    }

    /**
     * 获取用户的个人信息
     * @param openId
     * @return
     */
    public String getUserInfo(String openId){
        UserInfoDomain result = userInfoDao.getUserInfo(openId);
        return gson.toJson(result);
    }
}
