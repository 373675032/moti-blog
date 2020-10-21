package com.moti.entity;

import java.io.Serializable;

/**
 * (Front)实体类
 *
 * @author 莫提
 * @since 2020-09-21 10:22:27
 */
public class Front implements Serializable {

    private static final long serialVersionUID = -80542390247178033L;

    /**
    * 主键ID
    */
    private Integer id;

    /**
    * 背景图片
    */
    private String background;

    /**
    * 公告
    */
    private String notice;

    /**
    * 公告图片的URL
    */
    private String imgTarget;

    /**
    * 公告图片
    */
    private String noticeImg;

    /**
    * 站点标题
    */
    private String title;

    /**
    * 副标题
    */
    private String subTitle;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getImgTarget() {
        return imgTarget;
    }

    public void setImgTarget(String imgTarget) {
        this.imgTarget = imgTarget;
    }

    public String getNoticeImg() {
        return noticeImg;
    }

    public void setNoticeImg(String noticeImg) {
        this.noticeImg = noticeImg;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    @Override
    public String toString() {
        return "Front{" +
                "id=" + id +
                "background=" + background +
                "notice=" + notice +
                "imgTarget=" + imgTarget +
                "noticeImg=" + noticeImg +
                "title=" + title +
                "subTitle=" + subTitle +
                 '}';      
    }
}