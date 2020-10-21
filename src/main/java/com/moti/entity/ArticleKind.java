package com.moti.entity;

import java.io.Serializable;

/**
 * (ArticleKind)实体类
 *
 * @author 莫提
 * @since 2020-09-09 09:36:47
 */
public class ArticleKind implements Serializable {

    private static final long serialVersionUID = 358621785759619121L;

    /**
    * 文章ID
    */
    private Integer articleId;

    /**
    * 类别ID
    */
    private Integer kindId;

    public ArticleKind(Integer articleId, Integer kindId) {
        this.articleId = articleId;
        this.kindId = kindId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getKindId() {
        return kindId;
    }

    public void setKindId(Integer kindId) {
        this.kindId = kindId;
    }

    @Override
    public String toString() {
        return "ArticleKind{" +
                "articleId=" + articleId +
                "kindId=" + kindId +
                 '}';      
    }
}