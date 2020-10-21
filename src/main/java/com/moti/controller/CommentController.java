package com.moti.controller;

import com.alibaba.fastjson.JSONObject;
import com.moti.dto.Email;
import com.moti.entity.Comment;
import com.moti.redis.CommentKey;
import com.moti.utils.*;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName: CommentController
 * @Description: 评论控制器
 * @author: 莫提
 * @date 2020/9/21 19:27
 * @Version: 1.0
 **/
@RestController
public class CommentController extends BaseController {

    private Logger logger = LogUtils.getInstance(CommentController.class);

    /**
     * 添加评论
     */
    @PostMapping("/addComment")
    public String addComment(Comment comment){
        comment.setType(0);
        comment.setTime(new Date());
        comment.setImg(ImageUtils.getRandomImg());
        comment.setStatus(0);
        comment.setIp(IPUtils.getIpAddress(request));
        if (commentService.insert(comment)) {
            result.setCode(200);
            CacheUtils.cleanCommentCache(redisService,comment.getArticleId());
            logger.warn("【成功】添加评论");
        }else {
            result.setCode(500);
            logger.warn("【失败】添加评论");
        }
        redisService.del(CommentKey.getByArticleId,String.valueOf(comment.getArticleId()));
        return JSONObject.toJSONString(result);
    }

    /**
     * 回复评论
     */
    @PostMapping("/replyComment")
    public String replyComment(Comment comment,boolean sentEmail){
        // 获取被回复的留言
        Comment reply = commentService.queryById(comment.getReplyId());
        if (sentEmail){
            String title = articleService.getTitle(reply.getArticleId());
            // 获得访问地址
            String url = request.getRequestURL().toString();
            String[] strings = url.split("/moti-blog");
            url = strings[0]+"/moti-blog/article?id="+reply.getArticleId();
            Email email = new Email(reply.getEmail(),comment.getContent(),title,url);
            String string = BeanUtils.beanToString(email);
            // 发布消息
            rabbitTemplate.convertAndSend("email", string);
        }
        comment.setTime(new Date());
        comment.setType(1);
        comment.setArticleId(reply.getArticleId());

        if (commentService.insert(comment)) {
            result.setCode(200);
            CacheUtils.cleanCommentCache(redisService,comment.getArticleId());
            logger.warn("【成功】评论回复");
        }else {
            result.setCode(500);
            logger.warn("【失败】评论回复");
        }
        redisService.del(CommentKey.getByArticleId,String.valueOf(reply.getArticleId()));
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除评论
     */
    @GetMapping("/deleteComment")
    public String deleteComment(@RequestParam("id") Integer id){
        Comment comment = commentService.queryById(id);
        redisService.del(CommentKey.getByArticleId,String.valueOf(comment.getArticleId()));
        if (commentService.deleteById(id)) {
            commentService.deleteByReplyId(id);
            result.setCode(200);
            CacheUtils.cleanCommentCache(redisService,comment.getArticleId());
            logger.warn("【成功】删除评论");
        }else {
            result.setCode(500);
            logger.warn("【失败】删除评论");
        }
        redisService.del(CommentKey.getByArticleId,String.valueOf(comment.getArticleId()));
        return JSONObject.toJSONString(result);
    }


}
