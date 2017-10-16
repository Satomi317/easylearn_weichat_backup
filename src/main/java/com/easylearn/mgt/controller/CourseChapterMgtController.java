package com.easylearn.mgt.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.mgt.service.CourseChapterMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CourseChapterMgtController extends MvcComponent{
    @Autowired
    private CourseChapterMgtService courseChapterMgtService ;
    /**
     * 查询数据库所有章节列表接口
     */
    @RequestMapping("/queryAllChapterInfo")
    @ResponseBody
    public String queryAllChapterInfo(){
        logger.info("开始查询数据库所有章节信息.. ");
        return courseChapterMgtService.queryAllChapterInfo();
    }
    /**
     * 新增章节接口
     */
    @RequestMapping("/addNewChapter")
    @ResponseBody
    public String addNewChapter(HttpServletRequest request){
        logger.info("从请求中获取新增章节信息");
        int chapterNum = Integer.parseInt(request.getParameter("chapterNum")) ;
        String chapterTitle = request.getParameter("chapterTitle") ;
        int courseNum = Integer.parseInt(request.getParameter("courseNum")) ;
        String courseImg = request.getParameter("courseImg") ;
        try{
            courseChapterMgtService.addNewChapter(chapterNum,chapterTitle,courseNum,courseImg);
            logger.info("新增章节成功");
            return "Success" ;
        }catch (Exception e){
            logger.error("新增章节失败，请检查chapterNum是否重复" +e);
            return "新增章节失败，请检查chapterNum是否重复" ;
        }
    }
}
