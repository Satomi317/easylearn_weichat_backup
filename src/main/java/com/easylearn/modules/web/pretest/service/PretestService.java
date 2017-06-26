package com.easylearn.modules.web.pretest.service;

import com.easylearn.comm.MvcComponent;
import com.easylearn.modules.web.pretest.dao.PretestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.easylearn.modules.web.pretest.domain.PretestDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by CZH on 2017/6/17.
 */
@Service
public class PretestService extends MvcComponent{
    @Autowired
    private PretestDao pretestDao;

    /**
     * 抽取课前测试的题目
     * @return
     */
    public String getQuestions(){
        //获取题库中的40道题 0-23为词汇题  24-39为听力题
        List<PretestDomain> result = pretestDao.findQuestions();
        ArrayList wordNums = RandomList(23,0,6);
        ArrayList listenNums = RandomList(39,24,4);
        wordNums.addAll(listenNums);

        List<PretestDomain> questions = new ArrayList<>();
        wordNums.forEach(item->{
            int index = Integer.parseInt(item.toString());
            questions.add(result.get(index));
        });
        String jsonStr = gson.toJson(questions);
        return jsonStr;
    }

    /**
     * 生成范围内指定个随机数
     * @param max   范围上限
     * @param min   范围下限
     * @param nums  个数
     * @return
     */
    private ArrayList RandomList(int max, int min, int nums){
        ArrayList result = new ArrayList();
        Random random = new Random();

        while(result.size() != nums){
            int r = random.nextInt(max-min + 1) + min;
            if(!result.contains(r)){
                result.add(r);
            }
        }
        return result;
    }
}
