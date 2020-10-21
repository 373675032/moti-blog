package com.moti.entity;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Date;

/**
 * (Statistics)实体类
 *
 * @author 莫提
 * @since 2020-10-05 20:09:53
 */
public class Statistics implements Serializable {

    private static final long serialVersionUID = 686994831199584266L;

    /**
    * 主键ID
    */
    private Integer id;

    /**
    * ip地址
    */
    private String ip;

    /**
    * 访问次数
    */
    private Integer requestCount;

    /**
    * 日期
    */
    private Date requestDate;

    /**
    * 访问的文章id。如果为-1表示访问主页
    */
    private Integer articleId;

    public Statistics() {
    }

    public Statistics(String ip, Integer requestCount, Date requestDate, Integer articleId) {
        this.ip = ip;
        this.requestCount = requestCount;
        this.requestDate = requestDate;
        this.articleId = articleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Integer requestCount) {
        this.requestCount = requestCount;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "id=" + id +
                "ip=" + ip +
                "requestCount=" + requestCount +
                "requestDate=" + requestDate +
                "articleId=" + articleId +
                 '}';      
    }
}