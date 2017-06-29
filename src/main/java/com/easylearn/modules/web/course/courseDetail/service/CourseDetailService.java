package com.easylearn.modules.web.course.courseDetail.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.course.courseDetail.dao.CourseDetailDao;
import com.easylearn.modules.web.course.courseDetail.doamin.ContentBean;
import com.easylearn.modules.web.course.courseDetail.doamin.CourseFileDomain;
import com.easylearn.modules.web.course.courseDetail.doamin.CoursePartDomain;
import com.easylearn.modules.web.course.courseDetail.doamin.ResultBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CZH on 2017/6/29.
 */
@Service
public class CourseDetailService extends MvcComponent {
    @Autowired
    private CourseDetailDao courseDetailDao;

    public String getCourseDetail(int chapterNum){
        //查询数据
        List<CoursePartDomain> partResult = courseDetailDao.getCoursePart(chapterNum);
        List<CourseFileDomain> fileResult = courseDetailDao.getCourseFile(partResult);
        //组装返回结果
        List<ResultBean> result = new ArrayList<>();
        for(int i = 0; i < partResult.size(); i++){
            CoursePartDomain tempBean = partResult.get(i);
            long partNum = tempBean.getPartNum();
            ResultBean bean = new ResultBean();
            bean.setPartTitle(tempBean.getPartTitle());     //设置part的标题
            bean.setImgUrl(tempBean.getImgPath());          //设置part的图片路径
            bean.setAudioPath(tempBean.getAudioPath());     //设置part的语音路径

            //组装单词语句内容
            List<ContentBean> contentList = new ArrayList<>();
            for(int j = 0; j < fileResult.size(); j++){
                CourseFileDomain temp = fileResult.get(j);
                if(temp.getPartNum() == partNum && "word".equals(temp.getFileType())){
                    ContentBean contentBean = new ContentBean();
                    contentBean.setAudioSrc(temp.getAudioPath());   //设置语音路径
                    contentBean.setContent(temp.getFileName());     //设置听力内容
                    contentList.add(contentBean);
                }
            }
            bean.setAudioContent(contentList);              //设置part的语句内容

            result.add(bean);       //添加至结果中
        }

        return gson.toJson(result);
    }
}
