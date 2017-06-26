package com.easylearn.modules.web.pretest.dao;

import com.easylearn.modules.web.pretest.domain.PretestDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Created by CZH on 2017/6/17.
 */
@Repository
public class PretestDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<PretestDomain> findQuestions(){
        String querySql = "SELECT * FROM question_base";
        Map paraMap = new HashMap();
        List<PretestDomain> result = namedParameterJdbcTemplate.queryForList(querySql,paraMap);
        return result;
    }
}
