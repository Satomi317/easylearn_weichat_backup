package com.easylearn.modules.web.exchange.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.exchange.protocol.ProtocolIn;
import com.easylearn.modules.web.exchange.protocol.ProtocolOut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by CZH on 2017/6/27.
 */
@Controller
public class ExchangeController extends MvcComponent {

    @RequestMapping(value = "exchangeBonus",method = RequestMethod.POST)
    public void exchangeBonus(@RequestBody ProtocolIn protocolIn, HttpServletResponse response)throws IOException {
        response.setCharacterEncoding("UTF-8");
        String signature = protocolIn.getSignature();

        ProtocolOut result = new ProtocolOut();
        result.setMessage("ok");
        result.setSuccess(true);
        result.setResult(signature);

        String resp = gson.toJson(result);
        logger.info(resp);
        PrintWriter out = response.getWriter();
        out.print(resp);
        out.close();
    }
}
