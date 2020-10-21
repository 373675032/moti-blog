package com.moti.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * (Admin)实体类
 *
 * @author 莫提
 * @since 2020-08-30 22:34:34
 */
public class Admin implements Serializable {
    
    private static final long serialVersionUID = -10283078166005266L;
    
    /**
    * 主键ID
    */
    private Integer id;
    
    /**
    * 绑定QQ登录的ID
    */
    private String openId;
    
    /**
    * 头像地址
    */
    private String img;
    
    /**
    * 作者昵称
    */
    private String name;
    
    /**
    * 登录密码
    */
    private String password;
    
    /**
    * 加密盐
    */
    private String salt;
    
    /**
    * 性别：男、女、保密
    */
    private String sex;
    
    /**
    * 出生年月日
    */
    private Date birthday;
    
    /**
    * 地区
    */
    private String address;
    
    /**
    * 邮箱
    */
    private String email;
    
    /**
    * 手机号码
    */
    private String phone;
    
    /**
    * QQ号码
    */
    private String qq;
    
    /**
    * 微信号图片地址
    */
    private String wechat;
    
    /**
    * 微信公众号图片地址
    */
    private String publicWechat;
    
    /**
    * 职业
    */
    private String career;
    
    /**
     * 个人说明
     */
    private String info;
    
    /**
    * 最近登录
    */
    private Date recentLogin;

    public Admin() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPublicWechat() {
        return publicWechat;
    }

    public void setPublicWechat(String publicWechat) {
        this.publicWechat = publicWechat;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Date getRecentLogin() {
        return recentLogin;
    }

    public void setRecentLogin(Date recentLogin) {
        this.recentLogin = recentLogin;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", openId='" + openId + '\'' +
                ", img='" + img + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", wechat='" + wechat + '\'' +
                ", publicWechat='" + publicWechat + '\'' +
                ", career='" + career + '\'' +
                ", info='" + info + '\'' +
                ", recentLogin=" + recentLogin +
                '}';
    }
}