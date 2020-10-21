package com.moti.entity;

import java.io.Serializable;

/**
 * (Link)实体类
 *
 * @author 莫提
 * @since 2020-09-21 15:01:32
 */
public class Link implements Serializable {

    private static final long serialVersionUID = 564121663147423979L;

    /**
    * 主键ID
    */
    private Integer id;

    /**
    * 名称
    */
    private String name;

    /**
    * 链接地址
    */
    private String url;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                "name=" + name +
                "url=" + url +
                 '}';      
    }
}