package com.easylearn.mgt.controller;

import com.easylearn.comm.MvcComponent;
import com.easylearn.mgt.domain.CourseFileDomain;
import com.easylearn.mgt.service.CourseFileMgtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class CourseFileMgtController extends MvcComponent {
    @Autowired
    private CourseFileMgtService courseFileMgtService ;

    /**
     * 根据partNum查询文件信息
     */
    @RequestMapping("queryFileInfoByPartNum")
    @ResponseBody
    public String queryFileInfoByPartNum(HttpServletRequest request) {
        int partNum = Integer.parseInt(request.getParameter("partNum")) ;
        return courseFileMgtService.queryFileInfoByPartNum(partNum) ;
    }

    /**
     * 批量向数据库中新增文件信息
     */
    @RequestMapping("addNewFiles")
    @ResponseBody
    public String addNewFiles(HttpServletRequest request) {
        String paraJson = request.getParameter("paraJson") ;
        CourseFileDomain[] courseFileDomainArray = gson.fromJson(paraJson,CourseFileDomain[].class) ;
        // 对象数组转为List
        List<CourseFileDomain> paraList = Arrays.asList(courseFileDomainArray) ;
        if (courseFileMgtService.addNewFiles(paraList)){
            logger.info("插入多条File信息成功，Controller方法正常返回");
            return "Success" ;
        }else {
            logger.error("插入文件信息失败");
            return "false" ;
        }
    }
}
