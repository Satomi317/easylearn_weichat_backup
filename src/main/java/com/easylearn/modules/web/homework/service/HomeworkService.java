package com.easylearn.modules.web.homework.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.homework.dao.HomeworkDao;
import com.easylearn.modules.web.homework.domain.HomeworkDomain;
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

    public String insertHomework(String localId,String openId,String timetamp,long chapterNum){
        homeworkDao.insertHomework(localId,openId,timetamp,chapterNum);
        return "";
    }

    public String getUserHomework(String openId){
        List<HomeworkDomain> result = homeworkDao.getUserHomework(openId);
        return gson.toJson(result);
    }
}
