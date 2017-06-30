package com.easylearn.modules.web.exchange.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.exchange.protocol.ProtocolIn;
import com.easylearn.modules.web.exchange.protocol.ProtocolOut;
import com.easylearn.modules.web.exchange.service.BonusListService;
import com.easylearn.modules.web.exchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by CZH on 2017/6/27.
 */
@Controller
public class ExchangeController extends MvcComponent {
    @Autowired
    private BonusListService bonusListService;

    @Autowired
    private ExchangeService exchangeService;

    /**
     * 兑换积分请求接口
     * @param protocolIn
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "exchangeBonus",method = RequestMethod.POST)
    public void exchangeBonus(@RequestBody ProtocolIn protocolIn, HttpServletRequest request, HttpServletResponse response)throws IOException {
        response.setCharacterEncoding("UTF-8");
        ProtocolOut result = new ProtocolOut();

        //从session获取用户的openId
        HttpSession session = request.getSession();
        String openId = (String) session.getAttribute("openId");

        if(openId != null && openId != ""){
            result = exchangeService.doExchange(protocolIn,openId);
        }else{
            result.setMessage("error:用户未登录,session信息不存在。");
            result.setSuccess(false);
            result.setResult("");
        }

        //发送响应
        String resp = gson.toJson(result);
        logger.info(resp);
        PrintWriter out = response.getWriter();
        out.print(resp);
        out.close();
    }

    /**
     * 获取所有兑换物品的列表
     * 根据积分需求从小到大排序
     * @return
     */
    @RequestMapping("/getBonusList")
    @ResponseBody
    public String getBonusList(){
        String res = bonusListService.getBonusList();
        return res;
    }
}
