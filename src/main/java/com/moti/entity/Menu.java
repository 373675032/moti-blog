package com.moti.entity;

import java.io.Serializable;

/**
 * (Menu)实体类
 *
 * @author 莫提
 * @since 2020-09-21 14:13:23
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = 746377685414285563L;

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

    /**
    * 位置【0：左】【1：右】
    */
    private Integer location;

    public Menu() {
    }

    public Menu(Integer location) {
        this.location = location;
    }

    public Menu(String name, String url, Integer location) {
        this.name = name;
        this.url = url;
        this.location = location;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                "name=" + name +
                "url=" + url +
                "location=" + location +
                 '}';      
    }
}