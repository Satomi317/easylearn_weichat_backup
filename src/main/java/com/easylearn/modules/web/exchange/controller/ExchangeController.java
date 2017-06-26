package com.easylearn.modules.web.exchange.controller;

import com.easylearn.comm.MvcComponent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by CZH on 2017/6/27.
 */
@Controller
public class ExchangeController extends MvcComponent {

    @RequestMapping(value = "exchangeBonus",method = RequestMethod.POST)
    public void exchangeBonus(HttpServletRequest request, HttpServletResponse response)throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String signature = request.getParameter("signature");
        String resp = "{\"name\":\""+ signature +"\"}";
        logger.info(resp);
        PrintWriter out = response.getWriter();
        out.print(resp);
        out.close();
    }
}
