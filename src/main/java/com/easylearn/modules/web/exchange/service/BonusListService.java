package com.easylearn.modules.web.exchange.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.exchange.dao.BonusListDao;
import com.easylearn.modules.web.exchange.domain.BonusListDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by CZH on 2017/6/27.
 */
@Service
public class BonusListService  extends MvcComponent {
    @Autowired
    private BonusListDao bonusListDao;

    public String getBonusList(){
        List<BonusListDomain> reslt =  bonusListDao.getBonusList();
        return gson.toJson(reslt);
    }

}
