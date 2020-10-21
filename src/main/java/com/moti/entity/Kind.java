package com.moti.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * (Kind)实体类
 *
 * @author 莫提
 * @since 2020-09-02 20:10:48
 */
public class Kind implements Serializable {

    private static final long serialVersionUID = 837072739577740886L;

    /**
    * 类别的主键ID
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
    * 类别的介绍
    */
    private String introduce;

    /**
    * 类别头图地址
    */
    private String img;

    public Kind() {
    }

    public Kind(String name) {
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
        return "Kind{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", articleCount=" + articleCount +
                ", introduce='" + introduce + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}