package com.moti.controller;

import com.moti.dto.ResponseResult;
import com.moti.elasticsearch.ArticleRespository;
import com.moti.entity.Admin;
import com.moti.entity.Front;
import com.moti.redis.RedisService;
import com.moti.service.*;
import com.moti.utils.MailUtils;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: BaseController
 * @Description: 控制器的基类，所有控制器必须继承此类
 * @author: 莫提
 * @date 2020/9/2 19:55
 * @Version: 1.0
 **/
public class BaseController {

    @Autowired
    AdminService adminService;
    @Autowired
    ArticleService articleService;
    @Autowired
    KindService kindService;
    @Autowired
    TagService tagService;
    @Autowired
    FrontService frontService;
    @Autowired
    MenuService menuService;
    @Autowired
    LinkService linkService;
    @Autowired
    CommentService commentService;
    @Autowired
    StatisticsService statisticsService;


    @Autowired
    RedisService redisService;
    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    RestHighLevelClient client;
    @Autowired
    ArticleRespository articleRespository;


    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;


    protected ResponseResult result;
    protected Admin loginAdmin;
    protected Admin user;
    protected Front front;


    /**
     * 在每个子类方法调用之前先调用，设置request,response,session这三个对象
     */
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
        result = new ResponseResult();
        // 设置admin对象
        loginAdmin = (Admin)session.getAttribute("admin");
        // 获取前台主页对象
        front = frontService.queryById(1);
        // 获取用户对象
        user = adminService.queryById(1);
        user.setPassword(null);
        user.setRecentLogin(null);
        user.setOpenId(null);
        user.setSalt(null);
    }
}
