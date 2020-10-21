package com.moti.utils;

import com.moti.controller.KindController;
import com.moti.entity.Article;
import com.moti.redis.*;
import com.moti.service.ArticleService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: CacheUtils
 * @Description: TODO
 * @author: 莫提
 * @date 2020/10/10 23:25
 * @Version: 1.0
 **/
@Component
public class CacheUtils {

    private static Logger logger = LogUtils.getInstance(CacheUtils.class);

    /**
     * 清理文集缓存
     */
    public static void cleanKindCache(RedisService redisService,Integer id){
        redisService.del(KindKey.getIndex,"");
        if (id != null){
            redisService.del(KindKey.getById,String.valueOf(id));
        }
        logger.warn("【Redis】清理文集缓存");
    }

    /**
     * 清理标签缓存
     */
    public static void cleanTagCache(RedisService redisService,Integer id){
        redisService.del(TagKey.getIndex,"");
        if (id != null){
            redisService.del(TagKey.getById,String.valueOf(id));
        }
        logger.warn("【Redis】清理标签缓存");
    }

    /**
     * 清理缓存
     */
    public static void cleanArticleCacheByTagId(ArticleService articleService,RedisService redisService,Integer tId){
        redisService.del(ArticleKey.getIndex,"");
        List<Article> articles = articleService.findByTagId(tId);
        articles.forEach(article -> {
            redisService.del(ArticleKey.getById,String.valueOf(article.getId()));
        });
        logger.warn("【Redis】清理标签涉及的文章缓存，KindId："+tId);
    }

    /**
     * 清理管理员缓存
     */
    public static void cleanAdminCache(RedisService redisService){
        redisService.del(AdminKey.getById,"1");
        redisService.del(AdminKey.getByFront,"1");
        logger.warn("【Redis】清理标签缓存");
    }

    /**
     * 清理文章缓存
     */
    public static void cleanArticleCache(RedisService redisService,Integer id){
        redisService.del(ArticleKey.getIndex);
        redisService.del(ArticleKey.getfamousArticles,"");
        redisService.del(ArchivesKey.getIndex,"");
        redisService.del(TagKey.getIndex,"");
        redisService.del(KindKey.getIndex,"");
        if (id != null){
            redisService.del(ArticleKey.getById,String.valueOf(id));
        }
    }

    /**
     * 清理评论缓存
     */
    public static void cleanCommentCache(RedisService redisService,Integer aId){
        redisService.del(CommentKey.getByArticleId,String.valueOf(aId));
    }

    /**
     * 清空Redis数据库
     */
    public static void flush(RedisService redisService){
        redisService.flush();
    }

}
