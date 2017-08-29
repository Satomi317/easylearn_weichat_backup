package com.easylearn.test.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.baseservice.beans.CustomerArticle;
import com.easylearn.modules.baseservice.beans.NewsContent;
import com.easylearn.modules.baseservice.service.CoreService;
import com.easylearn.test.Dao.CourseChapterDao;
import com.easylearn.test.Dao.UserCourseDao;
import com.easylearn.test.domain.CourseChapterDomain;
import com.easylearn.test.domain.UserCourseDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${serverAddress}")
    public String serverAddress;

    //调用客服接口推送图文消息图片地址
    @Value("${pushPictureUrl}")
    public String pushPicUrl;

    @Autowired
    private CoreService coreService;

    @Autowired
    private CourseChapterDao courseChapterDao;

    @Autowired
    private UserCourseDao userCourseDao;

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

    @Scheduled(cron = "0 0 20 ? * *")
    public void cronJob() {
        logger.info("开始执行定时任务");

        String curTime = Long.toString(System.currentTimeMillis());

        //推送零基础课程
        List<UserCourseDomain> zeroBaseUsers = userCourseDao.getAllZeroBaseUser(curTime);
        for(UserCourseDomain user:zeroBaseUsers){
            //推送的图文消息
            NewsContent newsContent = new NewsContent();
            List<CustomerArticle> articles = new ArrayList<>();

            CustomerArticle article = createArticle(user);

            articles.add(article);
            newsContent.setArticles(articles);
            String resp = coreService.pushNewsMessageByCustomer(user.getOpenId(),newsContent);
            logger.info("执行定时器推送零基础课程,结果为："+resp);
            userCourseDao.updatePushNumber(user.getOpenId(),"0",curTime);
        }

        //推送微基础课程
        List<UserCourseDomain> tinyBaseUsers = userCourseDao.getAllTinyBaseUser(curTime);
        for(UserCourseDomain user:tinyBaseUsers){
            //推送的图文消息
            NewsContent newsContent = new NewsContent();
            List<CustomerArticle> articles = new ArrayList<>();

            CustomerArticle article = createArticle(user);

            articles.add(article);
            newsContent.setArticles(articles);
            String resp = coreService.pushNewsMessageByCustomer(user.getOpenId(),newsContent);
            logger.info("执行定时器推送微基础课程,结果为："+resp);
            userCourseDao.updatePushNumber(user.getOpenId(),"1",curTime);
        }

        //推送试听课程
        List<UserCourseDomain> demoUsers = userCourseDao.getAllDemoUser();
        for(UserCourseDomain user:demoUsers){
            //推送的图文消息
            NewsContent newsContent = new NewsContent();
            List<CustomerArticle> articles = new ArrayList<>();

            CustomerArticle article = createArticle(user);

            articles.add(article);
            newsContent.setArticles(articles);
            String resp = coreService.pushNewsMessageByCustomer(user.getOpenId(),newsContent);
            logger.info("执行定时器推送试听课程,结果为："+resp);
            //更新用户的推送记录
            userCourseDao.updateDemoPushNumber(user.getOpenId(),1);
        }

    }

    /**
     * 生产图文消息
     * @return
     */
    public CustomerArticle createArticle(UserCourseDomain userInfo){
        CustomerArticle article = new CustomerArticle();

        //推送次数
        int pushNumber = userInfo.getPushNumber();
        //课程类型
        String courseType = userInfo.getCourseType();
        int courseNumber = 0;
        if(courseType.equals("0")){
            //零基础
            courseNumber = 3;
        }else if(courseType.equals("1")){
            //微基础
            courseNumber = 4;
        }else if(courseType.equals("4")){
            //试听课
            courseNumber = 5;
        }

        //获取要推送的课程
        List<CourseChapterDomain> courseInfo = courseChapterDao.getPushCourse(pushNumber,courseNumber);
        if(courseInfo.size() != 0){
            String url = "http://" + serverAddress + "/#/chapter/" + courseInfo.get(0).getChapterNum() + "/openId/" + userInfo.getOpenId();
            article.setPicurl(courseInfo.get(0).getCourseImg());
            article.setUrl(url);
            article.setTitle(courseInfo.get(0).getChapterTitle());
            article.setDescription("快来点击学习今天的课程吧！");
        }else{
            article.setPicurl(pushPicUrl);
            article.setTitle("今日课程");
            article.setDescription("暂时没找到你今天要学的课程，点击菜单进去看看吧。");
        }
        return article;
    }
}
