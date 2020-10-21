package com.moti.entity;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

/**
 * (Comment)实体类
 *
 * @author 莫提
 * @since 2020-09-21 19:31:46
 */
public class Comment implements Serializable {

    private static final long serialVersionUID = -23488851154769108L;

    /**
    * 主键ID
    */
    private Integer id;

    /**
    * 文章ID
    */
    private Integer articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
    * 评论者昵称
    */
    private String name;

    /**
    * 邮箱地址
    */
    private String email;

    /**
    * 留言正文
    */
    private String content;

    /**
    * 作者回复的留言ID
    */
    private Integer replyId;

    /**
    * 随机的头像地址
    */
    private String img;

    /**
    * 留言时间
    */
    private Date time;

    /**
    * 评论类型【0：读者评论】【1：作者回复】
    */
    private Integer type;

    /**
    * 留言者的IP地址
    */
    private String ip;

    /**
     * 回复的留言
     */
    private List<Comment> reply;

    /**
     * 评论的状态【0：未读】【1：已读】
     */
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReplyId() {
        return replyId;
    }

    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Comment> getReply() {
        return reply;
    }

    public void setReply(List<Comment> reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", content=" + content +
                ", replyId=" + replyId +
                ", img='" + img + '\'' +
                ", time=" + time +
                ", type=" + type +
                ", ip='" + ip + '\'' +
                ", reply=" + reply +
                ", status=" + status +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}