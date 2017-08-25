package com.easylearn.modules.baseservice.beans;

import java.util.List;

/**
 * Created by yuisama on 2017/7/5.
 */
public class NewsContent {
   private List<CustomerArticle> articles;

    public List<CustomerArticle> getArticles() {
        return articles;
    }

    public void setArticles(List<CustomerArticle> articles) {
        this.articles = articles;
    }
}
