package com.moti.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * (Tag)实体类
 *
 * @author 莫提
 * @since 2020-09-02 20:10:48
 */
public class Tag implements Serializable {

    private static final long serialVersionUID = 366461946442962822L;

    /**
    * 标签的主键ID
    */
    private Integer id;

    /**
    * 名称
    */
    private String name;

    /**
    * 文章数
    */
    private Integer articleCount;

    /**
    * 标签的介绍
    */
    private String introduce;

    /**
    * 标签头图地址
    */
    private String img;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getArticleCount() {
        return articleCount;
    }

    public void setArticleCount(Integer articleCount) {
        this.articleCount = articleCount;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", articleCount=" + articleCount +
                ", introduce='" + introduce + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}