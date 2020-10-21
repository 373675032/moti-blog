package com.moti.controller;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.moti.entity.*;
import com.moti.redis.*;
import com.moti.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName: SystemController
 * @Description: 系统控制器，控制页面跳转
 * @author: 莫提
 * @date 2020/9/21 8:51
 * @Version: 1.0
 **/
@Controller
public class SystemController extends BaseController {

    private Logger logger = LogUtils.getInstance(AdminController.class);

    /**
     * 前往 前台首页
     */
    @GetMapping({"/","/index"})
    public String index(Map<String,Object> map,Integer page){
        logger.warn("请求【前台主页】");
        // 获取已发布的文章数量
        Integer totalCount = articleService.totalCount(1);
        Integer totalPage = (int)Math.ceil(totalCount/10+0.5);
        // 处理page参数
        if (ObjectUtils.isEmpty(page)) {
            page = 1;
        }else if (page < 1 || page > totalPage){
            return "error/401";
        }
        // 更新/新增 访问数据
        statisticsService.update(request,-1);
        // 获取主页的10篇文章
        List<Article> articles = articleService.orderByPublishTime(page);
        // 获取所有标签
        List<Tag> tags =  tags = tagService.queryAll();
        // 获取所有的文集
        List<Kind> kinds = kindService.queryAll();
        // 获取阅读量最高的10篇文章
        List<Article> famousArticles = articleService.orderByReadCount();
        // 获取日期归档
        List<ArticleDateArchive> archives = articleService.getArchive();
        // 获取友情链接
        List<Link> links = linkService.queryAll();
        // 获取左侧菜单
        List<Menu> leftMenus = menuService.getLeftMenus();
        // 获取右侧菜单
        List<Menu> rightMenus = menuService.getRightMenus();

        map.put("articles",articles);
        map.put("user",user);
        map.put("front",front);
        map.put("famousArticles",famousArticles);
        map.put("tags",tags);
        map.put("kinds",kinds);
        map.put("archives", archives);
        map.put("leftMenus",leftMenus);
        map.put("rightMenus",rightMenus);
        map.put("links",links);
        map.put("page",page);
        map.put("totalPage",totalPage);
        return "front/index";
    }

    /**
     * 前往 阅读文章页面
     */
    @GetMapping("/article")
    public String article(@RequestParam("id") Integer id, Map<String,Object> map){
        logger.warn("请求【阅读文章】页面，ID为 "+id);
        // 获取指定的文章
        Article article = articleService.queryById(id);
        if (article == null){
            logger.warn("【失败】文章不存在，返回404页面");
            return "error/404";
        }
        if (article.getStatus() == 3 && loginAdmin == null){
            logger.warn("【失败】无权限访问，返回401页面");
            return "error/401";
        }
        // 更新/新增 访问数据
        statisticsService.update(request,id);
        // 更新阅读量
        articleService.addReadCount(id);
        // 获取阅读量
        Integer readCount = articleService.getReadCount(id);
        // 获取所有标签
        List<Tag> tags = tagService.queryAll();
        // 获取所有文集
        List<Kind> kinds = kindService.queryAll();
        // 获取阅读量最高的10篇文章
        List<Article> famousArticles = articleService.orderByReadCount();
        // 获取日期归档
        List<ArticleDateArchive> archives = articleService.getArchive();
        // 获取所有的评论
        List<Comment> comments = commentService.queryByArticleIdAndType(id, 0);

        map.put("front",front);
        map.put("famousArticles",famousArticles);
        map.put("tags",tags);
        map.put("kinds",kinds);
        map.put("archives", archives);
        map.put("article",article);
        map.put("user",user);
        map.put("comments",comments);
        map.put("readCount",readCount);
        return "front/article";
    }

    /**
     * 前往 搜索文章页面
     */
    @GetMapping("/search")
    public String search(Map<String,Object> map,String content,Integer tagId,Integer kindId,String date){
        List<Article> articles = null;
        if (!ObjectUtils.isEmpty(content)){
            logger.warn("请求【搜索】页面，搜索内容："+content);
            // 初始化ES
            if (articleService.getEsDocCount() == 0){
                logger.warn("检测到【ElasticSearch为空】开始初始化..");
                articleService.initEs();
            }
            articles = articleService.searchFromEs(content);
            map.put("content",content);
        }else if (!ObjectUtils.isEmpty(tagId)){
            logger.warn("请求【搜索】页面，搜索标签ID："+tagId);
            articles = articleService.findByTagId(tagId);
            map.put("tag",tagService.queryById(tagId));
        }else if (!ObjectUtils.isEmpty(kindId)){
            logger.warn("请求【搜索】页面，搜索文集ID："+kindId);
            articles = articleService.findByKindId(kindId);
            map.put("kind",kindService.queryById(kindId));
        }else if (!ObjectUtils.isEmpty(date)){
            logger.warn("请求【搜索】页面，搜索日期范围："+date);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年 - MM月");
            try {
                Date parse = sdf.parse(date);
                articles = articleService.findByDate(parse);
                map.put("date",date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        map.put("articles",articles);
        map.put("front",front);
        map.put("user",user);
        logger.warn("【搜索结果】"+map);
        return "front/search";
    }

    /**
     * 前往 后台仪表盘页面
     */
    @GetMapping("/dashboard")
    public String dashboard(Map<String,Object> map){
        logger.warn("请求【后台仪表盘】页面");
        // 获取各个状态下文章的数量
        Map<String, Integer> countMap = statusCount();
        // 获取发布文章数
        map.put("publishCount",countMap.get("publish"));
        // 获取草稿数
        map.put("draftCount",countMap.get("draft"));
        // 获取回收站数
        map.put("trashCount",countMap.get("trash"));
        // 获取评论数
        map.put("commentCount",commentService.getCount());
        // 获取文集数
        map.put("kindCount",kindService.getCount());
        // 获取标签数
        map.put("tagCount",tagService.getCount());
        // 获取统计信息
        map.put("statistics",statisticsService.getVisitStatistics());
        // 获取统计数量信息
        map.put("statisticsCount",statisticsService.getStatisticsCount());
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/dashboard";
    }

    /**
     * 前往 我的资料页面
     */
    @GetMapping("/profile")
    public String profile(Map<String,Object> map){
        logger.warn("请求【我的资料】页面");
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/profile";
    }

    /**
     * 前往 所有文章页面
     */
    @GetMapping("/articles")
    public String articles(Map<String,Object> map,String type){
        List<Article> articles = new ArrayList<>();
        type = type == null ? "publish" : type;
        if ("publish".equals(type)){
            logger.warn("请求【已发表文章】页面");
            articles = articleService.queryByStatus(1);
        } else if ("draft".equals(type)){
            logger.warn("请求【草稿文章】页面");
            articles = articleService.queryByStatus(0);
        }else if ("trash".equals(type)){
            logger.warn("请求【回收站】页面");
            articles = articleService.queryByStatus(2);
        }else {
            return "error/400";
        }
        map.put("articles",articles);
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        // 获取各个状态下文章的数量
        map.put("statusCount",statusCount());
        return "admin/"+type;
    }

    /**
     * 前往 写文章/编辑文章页面
     */
    @GetMapping("/edit")
    public String edit(Map<String,Object> map,Integer id){
        // 获取全部的文集
        List<Kind> kinds = kindService.queryAll();
        map.put("kinds",kinds);
        if (id == null){
            logger.warn("请求【写文章】页面");
            return "admin/new";
        }else {
            logger.warn("请求【编辑文章】页面，ID："+id);
            // 获取文章对象
            Article article = articleService.queryById(id);
            // 获取文章的文集
            Kind kind = kindService.queryByArticleId(id);
            // 获取并处理文章的标签
            List<Tag> tags = tagService.queryByArticleId(id);
            StringBuilder tag = new StringBuilder(";");
            tags.forEach(temp -> {
                tag.append(temp.getName()+";");
            });
            map.put("article",article);
            map.put("kind",kind);
            map.put("tag",tag.toString());
            // 获取未读评论数
            map.put("unRead",commentService.getUnReadCount());
            return "admin/edit";
        }
    }

    /**
     * 前往 文集页面
     */
    @GetMapping("/kinds")
    public String kinds(Map<String,Object> map){
        logger.warn("请求【所有文集】页面");
        // 获取所有的文集
        List<Kind> kinds = kindService.queryAll();
        // 设置每个文集的文章数
        kinds.forEach(kind -> {
            kind.setArticleCount(kindService.getArticleCount(kind.getId()));
        });
        map.put("kinds",kinds);
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/kinds";
    }

    /**
     * 前往 编辑文集页面
     */
    @GetMapping("/kind")
    public String kind(Map<String,Object> map,Integer id){
        logger.warn("请求【编辑文集】页面");
        // 如果id为空那么重定向到所有文集页面
        if (id == null){
            return "redirect:/kinds";
        }
        // 获取此文集
        Kind kind = kindService.queryById(id);
        if (kind == null){
            return "redirect:/kinds";
        }
        map.put("kind",kind);
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/kind";
    }

    /**
     * 前往 所有标签页面
     */
    @GetMapping("/tags")
    public String tags(Map<String,Object> map){
        logger.warn("请求【所有标签】页面");
        // 获取所有的标签
        List<Tag> tags = tagService.queryAll();
        // 设置每个标签的文章数
        tags.forEach(tag -> {
            tag.setArticleCount(tagService.getArticleCount(tag.getId()));
        });
        map.put("tags",tags);
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/tags";
    }

    /**
     * 前往 编辑标签页面
     */
    @GetMapping("/tag")
    public String tag(Map<String,Object> map,Integer id){
        logger.warn("请求【编辑标签】页面");
        // 如果id为空那么重定向到所有标签页面
        if (id == null){
            return "redirect:/tags";
        }
        // 获取此标签
        Tag tag = tagService.queryById(id);
        if (tag == null){
            return "redirect:/tags";
        }
        map.put("tag",tag);
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/tag";
    }

    /**
     * 前往 所有评论页面
     */
    @GetMapping("/comments")
    public String comments(Map<String,Object> map){
        logger.warn("请求【所有评论】页面");
        Comment comment = new Comment();
        comment.setType(0);
        List<Comment> comments = commentService.queryAll(comment);
        comments.forEach(temp -> {
            temp.setTitle(articleService.getTitle(temp.getArticleId()));
        });
        map.put("comments",comments);
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/comments";
    }

    /**
     * 前往 阅读评论页面
     */
    @GetMapping("/comment")
    public String comment(Map<String,Object> map,@RequestParam("id") Integer id){
        logger.warn("请求【阅读评论】页面");
        Comment comment = commentService.queryById(id);
        comment.setStatus(1);
        commentService.update(comment);
        map.put("comment",comment);
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/comment";
    }

    /**
     * 前往 后台-外观-主页
     */
    @GetMapping("/front")
    public String front(Map<String,Object> map){
        logger.warn("请求【后台-外观-主页】页面");
        map.put("front",front);
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/front";
    }

    /**
     * 前往 后台-外观-菜单
     */
    @GetMapping("/menu")
    public String menu(Map<String,Object> map){
        logger.warn("请求【后台-外观-菜单】页面");
        // 获取全部菜单
        List<Menu> menus = menuService.queryAll();
        map.put("menus",menus);
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/menu";
    }

    /**
     * 前往 后台-外观-友情链接
     */
    @GetMapping("/link")
    public String link(Map<String,Object> map){
        logger.warn("请求【后台-外观-友情链接】页面");
        // 获取全部菜单
        List<Link> links = linkService.queryAll();
        map.put("links",links);
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/link";
    }

    /**
     * 前往 后台-统计
     */
    @GetMapping("/statistics")
    public String statistics(Map<String,Object> map){
        logger.warn("请求【后台-统计】页面");
        // 获取统计信息
        map.put("statistics",statisticsService.getVisitStatistics());
        // 获取所有访客信息
        map.put("visitors",statisticsService.getVisitorStatistics());
        // 获取统计数量信息
        map.put("statisticsCount",statisticsService.getStatisticsCount());
        // 获取未读评论数
        map.put("unRead",commentService.getUnReadCount());
        return "admin/statistics";
    }

    /**
     * 获取各种状态下文章的数量
     */
    public Map<String,Integer> statusCount(){
        Map<String,Integer> map = new HashMap<>();
        Integer draft = articleService.totalCount(0);
        Integer publish = articleService.totalCount(1);
        Integer trash = articleService.totalCount(2);
        map.put("draft",draft);
        map.put("publish",publish);
        map.put("trash",trash);
        logger.warn("获取各种状态下文章的数量:"+map);
        return map;
    }

}
