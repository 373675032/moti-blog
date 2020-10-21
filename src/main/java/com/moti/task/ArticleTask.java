package com.moti.task;

import com.moti.entity.Article;
import com.moti.rabbitmq.ArticleConsumer;
import com.moti.redis.ArticleKey;
import com.moti.service.BaseService;
import com.moti.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @ClassName: ArticleTask
 * @Description: 关于文章的定时器任务
 * @author: 莫提
 * @date 2020/10/9 14:36
 * @Version: 1.0
 **/
@Service
public class ArticleTask extends BaseService {

    private Logger logger = LogUtils.getInstance(ArticleTask.class);

    /**
     * 定时任务保存阅读量：每天凌晨0：00保存前一天晚上的阅读量
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void saveReadCount(){
        logger.warn("【定时任务】开始保存阅读量");
        long startTime = System.currentTimeMillis();
        Set<String> keys = redisService.keys(ArticleKey.getByReadCount);
        for (String key : keys) {
            String id = key.substring(key.lastIndexOf(":")+1);
            Integer count = redisService.get(ArticleKey.getByReadCount, id, Integer.class);
            Article article = new Article();
            article.setId(Integer.valueOf(id));
            article.setReadCount(count);
            articleMapper.update(article);
        }
        long endTime = System.currentTimeMillis();
        logger.warn("【定时任务】结束保存阅读量，"+"程序运行时间：" + (endTime - startTime) + "ms");
    }

    /**
     * 定时任务将数据保存到ES：每天凌晨0：00
     */
    @Scheduled(cron = "0 38 10 ? * *")
    public void saveToEs(){
        logger.warn("【定时任务】开始保存文章数据到ES");
        long startTime = System.currentTimeMillis();
        // 先清空再添加
        articleRespository.deleteAll();
        List<Article> articles = articleMapper.queryByStatus(1);
        for (Article article : articles) {
            article.setStatus(null);
            article.setKinds(null);
            article.setTags(null);
            article.setImg(null);
            article.setRecentEdit(null);
            articleRespository.save(article);
        }
        long endTime = System.currentTimeMillis();
        logger.warn("【定时任务】结束保存文章数据到ES，"+"程序运行时间：" + (endTime - startTime) + "ms");
        redisService.set(ArticleKey.getEsCount,"",articles.size(),60*60*24);
    }
}
