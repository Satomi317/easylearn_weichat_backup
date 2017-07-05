package com.easylearn.test.beans;

import com.easylearn.comm.MvcComponent;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

/**
 * Created by yuisama on 2017/7/5.
 */
@Service
public class JobBean extends MvcComponent implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        CoreService coreService = new CoreService();
//        //测试推送图文消息
//        String openId = "oGK1Zw7n-9m1YxeFgVvfwdCFfDb8";
//        NewsContent newsContent = new NewsContent();
//        List<Article> articles = new ArrayList<>();
//        Article article = new Article();
//        article.setPicurl("http://justtalk.oss-cn-shanghai.aliyuncs.com/image/%E7%9F%B3%E5%8E%9F.jpg");
//        article.setUrl("www.baidu.com");
//        article.setTitle("定时器推送测试");
//        article.setDescription("定时器推送成功");
//        articles.add(article);
//        newsContent.setArticles(articles);
//        String accessToken = restTemplate.getForObject("http://6488b855.ngrok.io/accessToken",String.class);
//        String resp = coreService.pushNewsMessageByCustomer(accessToken,openId,newsContent);
//        logger.info("执行定时器推送图文消息,结果为："+resp);
    }
}
