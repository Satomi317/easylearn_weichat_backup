package com.easylearn.test.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.baseservice.beans.Article;
import com.easylearn.modules.baseservice.beans.NewsContent;
import com.easylearn.modules.baseservice.service.CoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuisama on 2017/7/4.
 */
@Service
@EnableScheduling
public class TestQuartzService extends MvcComponent{
    @Autowired
    private CoreService coreService;

//    public String TestQuartzService(){
//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        Scheduler scheduler = null;
//        try{
//            scheduler = schedulerFactory.getScheduler();
//            JobDetail job= JobBuilder.newJob(JobBean.class).withIdentity("job1", "jgroup1").build();
//            //使用cornTrigger规则  每天10点42分
//            Trigger trigger= TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "triggerGroup")
//                    .withSchedule(CronScheduleBuilder.cronSchedule("0 49 14 * * ? *"))
//                    .startNow().build();
//            //把作业和触发器注册到任务调度中
//            scheduler.scheduleJob(job, trigger);
//            //启动调度
//            scheduler.start();
//            return "定时器执行成功";
//        }
//        catch (Exception e){
//            logger.error("定时器执行失败"+e);
//            return "定时器执行失败";
//        }
//    }

    @Scheduled(cron = "0 06 16 ? * *")
    public void cronJob() {
        logger.info("开始执行定时任务");
        //测试推送图文消息
        String openId = "oGK1Zw7n-9m1YxeFgVvfwdCFfDb8";
        NewsContent newsContent = new NewsContent();
        List<Article> articles = new ArrayList<>();
        Article article = new Article();
        article.setPicurl("http://justtalk.oss-cn-shanghai.aliyuncs.com/image/%E7%9F%B3%E5%8E%9F.jpg");
        article.setUrl("www.baidu.com");
        article.setTitle("定时器推送测试");
        article.setDescription("定时器推送成功");
        articles.add(article);
        newsContent.setArticles(articles);
        String resp = coreService.pushNewsMessageByCustomer(openId,newsContent);
        logger.info("执行定时器推送图文消息,结果为："+resp);
    }
}
