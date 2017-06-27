package com.easylearn.modules.web.exchange.service;

import com.easylearn.modules.web.exchange.dao.ExchangeDao;
import com.easylearn.modules.web.exchange.protocol.ProtocolIn;
import com.easylearn.modules.web.exchange.protocol.ProtocolOut;
import com.easylearn.modules.web.userInfo.dao.UserInfoDao;
import com.easylearn.modules.web.userInfo.domain.UserBonusDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by CZH on 2017/6/27.
 */
@Service
public class ExchangeService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private ExchangeDao exchangeDao;

    public ProtocolOut doExchange(ProtocolIn protocolIn,String openId){
        ProtocolOut protocolOut = new ProtocolOut();

        int exchangeItem = protocolIn.getExchangeItem();
        int exchangeDay = protocolIn.getExchangeDay();

        //兑换前再次进行积分查询
        UserBonusDomain bonusResult = userInfoDao.getUserBonus(openId);
        int userBonus = bonusResult.getBonus();

        if(userBonus < exchangeItem){
            protocolOut.setSuccess(false);
            protocolOut.setResult("抱歉，您的积分不足。");
            protocolOut.setMessage("error:积分不足");
        }else{
            //扣除积分，增加课程日期
            exchangeDao.updateBonus((userBonus-exchangeItem),openId);
            protocolOut.setSuccess(true);
            protocolOut.setResult("积分兑换成功！");
            protocolOut.setMessage("success");
        }
        return protocolOut;
    }
}
