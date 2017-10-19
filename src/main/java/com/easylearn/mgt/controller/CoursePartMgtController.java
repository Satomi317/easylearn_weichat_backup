package com.easylearn.mgt.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.mgt.domain.CoursePartDomain;
import com.easylearn.mgt.service.CoursePartMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class CoursePartMgtController extends MvcComponent{
    @Autowired
    private CoursePartMgtService coursePartMgtService;

    /**
     * 查询当前partNum最大值接口
     * @return int
     */
    @RequestMapping("queryMaxPartNum")
    @ResponseBody
    public int queryMaxPart(){
        return coursePartMgtService.queryMaxPartNum();
    }

    /**
     * 根据chapterNum查询part信息接口
     */
    @RequestMapping("queryPartInfoByChapterNum")
    @ResponseBody
    public String queryPartInfoByChapterNum(HttpServletRequest request){
        int chapterNum = Integer.parseInt(request.getParameter("chapterNum")) ;
        return coursePartMgtService.quertPartInfoByChapterNum(chapterNum) ;
    }

    /**
     * 一次向course_part表中插入多条数据
     */
    @RequestMapping("addNewParts")
    @ResponseBody
    public String aaddNewParts(HttpServletRequest request){
        String paraJson = request.getParameter("paraJson") ;
        CoursePartDomain[] coursePartDomainArray = gson.fromJson(paraJson,CoursePartDomain[].class) ;
        // 将对象数组转为List
        List<CoursePartDomain> paraList = Arrays.asList(coursePartDomainArray) ;
        if (coursePartMgtService.addNewParts(paraList)){
            logger.info("插入多条part信息成功") ;
            return "" + coursePartMgtService.queryMaxPartNum();
        }
        else {
            logger.error("插入part信息失败") ;
            return "false" ;
        }
    }
}
