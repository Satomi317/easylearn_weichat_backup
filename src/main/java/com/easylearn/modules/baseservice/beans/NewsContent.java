package com.easylearn.modules.baseservice.beans;

import java.util.List;

/**
 * Created by yuisama on 2017/7/5.
 */
public class NewsContent {
   private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
