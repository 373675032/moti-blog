package com.moti.entity;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * (Article)实体类
 *
 * @author 莫提
 * @since 2020-09-02 20:18:45
 */
@Document(indexName = "moti-blog",type = "article")
public class Article implements Serializable {

    private static final long serialVersionUID = -22440854516264684L;

    /**
    * 文章的主键ID
    */
    @Id
    private Integer id;

    /**
    * 文章标题
    */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String title;

    /**
    * 正文
    */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private Object content;

    /**
    * 发布时间
    */
    private Date publishTime;

    /**
    * 最近编辑时间
    */
    private Date recentEdit;

    /**
    * 文章状态。0：草稿，1：发布，2：回收站
    */
    @Field(store = false)
    private Integer status;

    /**
    * 阅读量
    */
    @Field(type = FieldType.Integer)
    private Integer readCount;

    /**
    * 文章介绍，引言
    */
    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String introduce;

    /**
    * 头图地址
    */
    @Field(store = false)
    private String img;

    /**
     * 文章分类/文集
     */
    @Field(store = false)
    private Kind kinds;

    /**
     * 文章标签
     */
    @Field(store = false)
    private List<Tag> tags;
    
    /**
     * 评论数
     */
    @Field(type = FieldType.Integer)
    private Integer comment;

    public Article() {
    }

    public Article(Integer status) {
        this.status = status;
    }

    public Article(Integer id, Integer readCount) {
        this.id = id;
        this.readCount = readCount;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getRecentEdit() {
        return recentEdit;
    }

    public void setRecentEdit(Date recentEdit) {
        this.recentEdit = recentEdit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
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

    public Kind getKinds() {
        return kinds;
    }

    public void setKinds(Kind kind) {
        this.kinds = kind;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content=" + content +
                ", publishTime=" + publishTime +
                ", recentEdit=" + recentEdit +
                ", status=" + status +
                ", readCount=" + readCount +
                ", introduce='" + introduce + '\'' +
                ", img='" + img + '\'' +
                ", kinds=" + kinds +
                ", tags=" + tags +
                ", comment=" + comment +
                '}';
    }
}