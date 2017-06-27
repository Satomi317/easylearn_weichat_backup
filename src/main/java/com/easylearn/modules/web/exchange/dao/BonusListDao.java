package com.easylearn.modules.web.exchange.dao;

import com.easylearn.modules.web.exchange.domain.BonusListDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CZH on 2017/6/27.
 */
@Repository
public class BonusListDao {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<BonusListDomain> getBonusList(){
        String querySql = "SELECT * FROM bonus_list ORDER BY BONUS";
        Map paraMap = new HashMap();
        List<BonusListDomain> result = namedParameterJdbcTemplate.query(querySql,paraMap,new BeanPropertyRowMapper<>(BonusListDomain.class));
        return  result;
    }
}
