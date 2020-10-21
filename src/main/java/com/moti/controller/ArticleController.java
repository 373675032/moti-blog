package com.moti.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.moti.entity.Article;
import com.moti.entity.Comment;
import com.moti.redis.*;
import com.moti.utils.CacheUtils;
import com.moti.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName: ArticleController
 * @Description: 文章控制器
 * @author: 莫提
 * @date 2020/9/2 19:55
 * @Version: 1.0
 **/
@Controller
public class ArticleController extends BaseController {

    private Logger logger = LogUtils.getInstance(AdminController.class);

    /**
     * 发布文章
     */
    @PostMapping("/publish")
    @ResponseBody
    public String publish(String title,String content,String tags,String kind,String introduce){
        Integer id = articleService.publish(title, content, tags, kind,introduce,1);
        result.setCode(200);
        logger.warn("【成功】文章发布成功，ID："+id);
        result.setData(id);
        CacheUtils.cleanArticleCache(redisService,null);
        rabbitTemplate.convertAndSend("es","article.save", id);
        redisService.incr(ArticleKey.getEsCount,"");
        return JSONObject.toJSONString(result);
    }

    /**
     * 保存草稿
     */
    @PostMapping("/draftPublish")
    @ResponseBody
    public String draftPublish(String title,String content,String tags,String kind,String introduce){
        Integer id = articleService.publish(title, content, tags, kind,introduce,0);
        result.setCode(200);
        logger.warn("【成功】文章草稿保存成功，ID："+id);
        result.setData(id);
        return JSONObject.toJSONString(result);
    }

    /**
     * 更新文章
     */
    @PostMapping("/update")
    @ResponseBody
    public String update(Integer id,String title,String content,String tags,String kind,String introduce,Integer status){
        if (articleService.update(id,title,content,tags,kind,introduce,status)) {
            result.setCode(200);
            logger.warn("【成功】文章更新成功，ID："+id);
            result.setData(id);
            CacheUtils.cleanArticleCache(redisService,id);
            if (status == 1){
                rabbitTemplate.convertAndSend("es","article.save", id);
            }
        }else {
            logger.warn("【失败】文章更新，没有修改任何信息");
        }

        return JSONObject.toJSONString(result);
    }

    /**
     * 轻删除【将文章移到回收站】
     */
    @GetMapping("/deleteLight")
    public String deleteLight(@RequestParam("id")Integer id, @RequestParam("from")String from){
        Article article = articleService.queryById(id);
        article.setStatus(2);
        if (articleService.update(article)) {
            logger.warn("【成功】将文章移入回收站，ID："+id);
            CacheUtils.cleanArticleCache(redisService,id);
            rabbitTemplate.convertAndSend("es","article.delete", id);
        }else {
            logger.warn("【失败】服务器异常,将文章移入回收站");
        }
        return "redirect:/articles?type="+from;
    }

    /**
     * 强删除【永久删除】
     */
    @GetMapping("/deleteStrong")
    public String deleteStrong(@RequestParam("id")Integer id, @RequestParam("from")String from){
        // 删除文章
        if (articleService.deleteById(id)) {
            logger.warn("【成功】将文章永久删除，ID："+id);
            CacheUtils.cleanArticleCache(redisService,id);
            commentService.deleteByArticleId(id);
        }else {
            logger.warn("【失败】服务器异常，将文章永久删除，ID："+id);
        }
        return "redirect:/articles?type="+from;
    }

    /**
     * 清空回收站
     */
    @GetMapping("/cleanTrash")
    public String cleanTrash(){
        if (articleService.deleteTrash() > 0) {
            logger.warn("【成功】清空回收站");
            CacheUtils.cleanArticleCache(redisService,null);
        }else {
            logger.warn("【失败】清空回收站");
        }
        return "redirect:/articles";
    }
}
