package com.moti.entity;

import java.io.Serializable;

/**
 * (ArticleTag)实体类
 *
 * @author 莫提
 * @since 2020-09-09 09:36:47
 */
public class ArticleTag implements Serializable {

    private static final long serialVersionUID = 434354772002267796L;

    /**
    * 文章ID
    */
    private Integer articleId;
    /**
    * 标签ID
    */
    private Integer tagId;

    public ArticleTag(Integer articleId, Integer tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public String toString() {
        return "ArticleTag{" +
                "articleId=" + articleId +
                "tagId=" + tagId +
                 '}';      
    }
}