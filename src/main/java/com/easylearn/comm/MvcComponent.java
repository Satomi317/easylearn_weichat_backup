package com.easylearn.comm;

import com.google.gson.Gson;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yuisama on 2017/5/12.
 */
public class MvcComponent {
    protected Log logger = LogFactory.getLog(getClass());
    protected RestTemplate restTemplate = new RestTemplate();
    protected Gson gson = new Gson();
}
