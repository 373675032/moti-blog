package com.moti.service.impl;

import com.github.pagehelper.PageHelper;
import com.moti.entity.*;
import com.moti.mapper.ArticleMapper;
import com.moti.redis.ArchivesKey;
import com.moti.redis.ArticleKey;
import com.moti.redis.TagKey;
import com.moti.service.ArticleService;
import com.moti.service.BaseService;
import com.moti.utils.ImageUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 莫提
 * @ClassName ArticleServiceImpl
 * @Description (Article)表服务实现类
 * @date 2020-09-02 20:18:45
 * @Version 1.0
 **/
@Service("articleService")
public class ArticleServiceImpl extends BaseService implements ArticleService {

    /**
     * @param article 实例对象
     * @return 是否成功
     * @Description 添加Article
     * @author 莫提
     * @date 2020-09-02 20:18:45
     */
    @Override
    public boolean insert(Article article) {
        if (articleMapper.insert(article) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @param id 主键
     * @return 是否成功
     * @Description 删除Article
     * @author 莫提
     * @date 2020-09-02 20:18:45
     */
    @Override
    public boolean deleteById(Integer id) {
        if (articleMapper.deleteById(id) == 1) {
            // 删除文集的关联信息
            articleKindMapper.deleteById(id);
            // 删除标签的关联信息
            articleTagMapper.deleteById(id);
            return true;
        }
        return false;
    }

    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-02 20:18:45
     */
    @Override
    public Article queryById(Integer id) {
        Article article = null;
        if (redisService.exists(ArticleKey.getById, String.valueOf(id))) {
            article = redisService.get(ArticleKey.getById, String.valueOf(id), Article.class);
        } else {
            article = articleMapper.queryById(id);
            if (article == null){
                return article;
            }
            // 获取该文章的分类
            Kind kinds = articleKindMapper.queryByArticleId(id);
            // 获取该文章的分类
            List<Tag> tags = articleTagMapper.queryByArticleId(id);
            article.setKinds(kinds);
            article.setTags(tags);
            if (!ObjectUtils.isEmpty(article)){
                redisService.set(ArticleKey.getById, String.valueOf(id), article, 60 * 60 * 24);
            }
        }
        return article;
    }

    /**
     * @param article 实例对象
     * @return 是否成功
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-02 20:18:45
     */
    @Override
    public boolean update(Article article) {
        if (articleMapper.update(article) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @return java.lang.Integer
     * @Description 根据条件获取文章数量
     * @Author 莫提
     * @Date 10:42 2020/9/3
     * @Param [article]
     **/
    @Override
    public Integer totalCount(Integer status) {
        return articleMapper.totalCount(status);
    }

    /**
     * @return boolean
     * @Description 发布文章
     * @Author 莫提
     * @Date 9:04 2020/9/9
     * @Param [title, content, tags, kind]
     **/
    @Override
    public Integer publish(String title, String content, String tags, String kind, String introduce, Integer status) {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setStatus(status);
        article.setComment(0);
        article.setImg(ImageUtils.getRandomFace());
        if (status != 0) {
            article.setPublishTime(new Date());
        }
        article.setRecentEdit(new Date());
        article.setReadCount(0);
        if ("".equals(introduce)) {
            // 摘要为空
            introduce = content.length() > 100 ? content.substring(0, 100) : content;
        }
        article.setIntroduce(introduce);
        articleMapper.insert(article);
        // 处理输入的标签集合
        if (!"".equals(tags)) {
            List<Tag> tagList = dealTag(tags);
            tagList.forEach(tag -> {
                List<Tag> list = tagMapper.queryAll(tag);
                if (list.size() == 0) {
                    // 标签不存在,先添加标签在添加关联
                    tag.setImg(ImageUtils.getRandomFace());
                    tagMapper.insert(tag);
                    articleTagMapper.insert(new ArticleTag(article.getId(), tag.getId()));
                } else {
                    // 标签已存在则添加数据到关联表
                    articleTagMapper.insert(new ArticleTag(article.getId(), list.get(0).getId()));
                }
            });
        }

        // 处理获取到的文集
        if (!"".equals(kind)) {
            List<Kind> kinds = kindMapper.queryAll(new Kind(kind));
            articleKindMapper.insert(new ArticleKind(article.getId(), kinds.get(0).getId()));
        }
        return article.getId();
    }

    /**
     * @return void
     * @Description 更新文章
     * @Author 莫提
     * @Date 9:32 2020/9/17
     * @Param [id, title, content, tags, kind, introduce, status]
     **/
    @Override
    public boolean update(Integer id, String title, String content, String tags, String kind, String introduce, Integer status) {
        // 首先获取文章
        Article article = articleMapper.queryById(id);
        article.setTitle(title);
        article.setContent(content);
        if ("".equals(introduce)) {
            // 摘要为空
            String s = content.replaceAll(" ", "");
            s = s.replaceAll("#", "");
            s = s.replaceAll("\n", "");
            if (s.length() > 100) {
                introduce = s.substring(0, 100);
            }
        }
        article.setIntroduce(introduce);
        article.setStatus(status);
        // 如果原来的文章发布时间为空
        if (ObjectUtils.isEmpty(article.getPublishTime())){
            article.setPublishTime(new Date());
        }
        if (articleMapper.update(article) == 1) {
            // 处理输入的标签集合
            articleTagMapper.deleteById(id);
            if (!"".equals(tags)) {
                List<Tag> tagList = dealTag(tags);
                tagList.forEach(tag -> {
                    List<Tag> list = tagMapper.queryAll(tag);
                    if (list.size() == 0) {
                        // 标签不存在,先添加标签在添加关联
                        tag.setImg(ImageUtils.getRandomFace());
                        tagMapper.insert(tag);
                        articleTagMapper.insert(new ArticleTag(article.getId(), tag.getId()));
                    } else {
                        ArticleTag articleTag = new ArticleTag(article.getId(), list.get(0).getId());
                        // 判断是否已经关联此标签
                        List<ArticleTag> result = articleTagMapper.queryAll(articleTag);
                        if (result.size() == 0) {
                            // 添加数据到关联表
                            articleTagMapper.insert(articleTag);
                        }
                    }
                });
            }
            // 处理文集
            articleKindMapper.deleteById(id);
            if (!"".equals(kind)) {
                List<Kind> kinds = kindMapper.queryAll(new Kind(kind));
                articleKindMapper.insert(new ArticleKind(article.getId(), kinds.get(0).getId()));
            }
            return true;
        }else {
            return false;
        }
    }

    /**
     * @return java.util.List<com.moti.entity.Article>
     * @Description 获取阅读量最高的文章
     * @Author 莫提
     * @Date 19:32 2020/9/14
     * @Param []
     **/
    @Override
    public List<Article> orderByReadCount() {
        List<Article> famousArticles = null;
        if (redisService.exists(ArticleKey.getfamousArticles, "")) {
            famousArticles = redisService.getList(ArticleKey.getfamousArticles, "", Article.class);
        } else {
            PageHelper.startPage(1, 10);
            famousArticles = getArticles(articleMapper.orderByReadCount());
            // 获取并存入缓存
            redisService.setList(ArticleKey.getfamousArticles, "", famousArticles);
        }
        return famousArticles;
    }

    /**
     * @return java.util.List<com.moti.entity.Article>
     * @Description 根据发表时间降序获取文章
     * @Author 莫提
     * @Date 19:32 2020/9/14
     * @Param []
     **/
    @Override
    public List<ArticleDateArchive> getArchive() {
        // 获取缓存中的日期归档
        List<ArticleDateArchive> archives = null;
        if (redisService.exists(ArchivesKey.getIndex, "")) {
            archives = redisService.getList(ArchivesKey.getIndex, "", ArticleDateArchive.class);
        } else {
            archives = articleMapper.getArchive();
            // 获取并存入缓存
            redisService.setList(ArchivesKey.getIndex, "", archives);
        }
        return archives;
    }

    /**
     * @return java.util.List<com.moti.entity.Article>
     * @Description 根据发表时间降序获取文章
     * @Author 莫提
     * @Date 19:32 2020/9/14
     * @Param []
     **/
    @Override
    public List<Article> orderByPublishTime(Integer page) {
        List<Article> articles = null;
        // 获取缓存中的文章
        if (redisService.exists(ArticleKey.getIndex,String.valueOf(page))) {
            articles = redisService.getList(ArticleKey.getIndex, String.valueOf(page), Article.class);
        } else {
            PageHelper.startPage(page,10);
            articles = getArticles(articleMapper.orderByPublishTime());
            // 获取并存入缓存
            redisService.setList(ArticleKey.getIndex,String.valueOf(page),articles);
        }
        return articles;
    }

    /**
     * @return void
     * @Description 阅读文章 ,阅读量加1
     * @Author 莫提
     * @Date 16:08 2020/9/20
     * @Param [id]
     **/
    @Override
    public void addReadCount(Integer id) {
        if (redisService.exists(ArticleKey.getByReadCount, String.valueOf(id))) {
            redisService.incr(ArticleKey.getByReadCount, String.valueOf(id));
        } else {
            Article article = articleMapper.queryById(id);
            redisService.set(ArticleKey.getByReadCount, String.valueOf(id), article.getReadCount() + 1, 0);
        }
    }

    /**
     * @return java.lang.Integer
     * @Description 根据Id获取阅读量
     * @Author 莫提
     * @Date 12:58 2020/10/9
     * @Param [id]
     **/
    @Override
    public Integer getReadCount(Integer id) {
        Integer count = 0;
        if (redisService.exists(ArticleKey.getByReadCount, String.valueOf(id))) {
            count = redisService.get(ArticleKey.getByReadCount, String.valueOf(id), Integer.class);
        } else {
            count = queryById(id).getReadCount();
            redisService.set(ArticleKey.getByReadCount, String.valueOf(id), count, 0);
        }
        return count;
    }

    /**
     * @return java.lang.String
     * @Description 根据Id获得文章标题
     * @Author 莫提
     * @Date 9:30 2020/9/22
     * @Param [aId]
     **/
    @Override
    public String getTitle(Integer aId) {
        return articleMapper.getTitle(aId);
    }

    /**
     * @return java.util.List<com.moti.entity.Article>
     * @Description 根据标签ID获取已发布的文章
     * @Author 莫提
     * @Date 13:34 2020/10/6
     * @Param [id]
     **/
    @Override
    public List<Article> findByTagId(Integer id) {
        return getArticles(articleMapper.findByTagId(id));
    }

    /**
     * @return java.util.List<com.moti.entity.Article>
     * @Description 根据文集ID获取已发布的文章
     * @Author 莫提
     * @Date 13:34 2020/10/6
     * @Param [id]
     **/
    @Override
    public List<Article> findByKindId(Integer id) {
        return getArticles(articleMapper.findByKindId(id));
    }

    /**
     * @return java.util.List<com.moti.entity.Article>
     * @Description 根据日期归档获取文章
     * @Author 莫提
     * @Date 13:58 2020/10/6
     * @Param [date]
     **/
    @Override
    public List<Article> findByDate(Date date) {
        return getArticles(articleMapper.findByDate(date));
    }

    /**
     * @return java.util.List<com.moti.entity.Article>
     * @Description 从ES中搜索结果并高亮显示
     * @Author 莫提
     * @Date 1:02 2020/10/10
     * @Param [content]
     **/
    @Override
    public List<Article> searchFromEs(String content) {
        List<Article> result = new ArrayList<>();
        // 创建搜索请求
        SearchRequest searchRequest = new SearchRequest("moti-blog");
        // 创建搜索对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 设置查询条件
        searchSourceBuilder.query(QueryBuilders.multiMatchQuery(content, "title", "content", "introduce"))
                .from(0)
                .size((int) getEsDocCount())
                .highlighter(new HighlightBuilder().field("*").requireFieldMatch(false).preTags("<span style='color:red;font-weight:500'>").postTags("</span>"));

        searchRequest.types("article").source(searchSourceBuilder);

        // 执行搜索
        SearchResponse response = null;
        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SearchHit[] hits = response.getHits().getHits();
        for (SearchHit hit : hits) {
            Map<String, Object> map = hit.getSourceAsMap();
            Article temp = new Article();
            temp.setId((Integer) map.get("id"));
            temp.setIntroduce(map.get("introduce").toString());
            temp.setPublishTime(new Date((Long) map.get("publishTime")));
            temp.setTitle(map.get("title").toString());
            temp.setReadCount((Integer) map.get("readCount"));
            temp.setComment((Integer) map.get("comment"));
            Map<String, HighlightField> fields = hit.getHighlightFields();
            // 设置标题高亮
            if (fields.containsKey("title")) {
                temp.setTitle(fields.get("title").fragments()[0].toString());
            }
            // 设置摘要高亮
            if (fields.containsKey("introduce")) {
                temp.setIntroduce(fields.get("introduce").fragments()[0].toString());
            }
            result.add(temp);
        }
        return getArticles(result);
    }

    /**
     * @return void
     * @Description 初始化ES
     * @Author 莫提
     * @Date 19:09 2020/10/10
     * @Param []
     **/
    @Override
    public void initEs() {
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
    }
    /**
     * @Description 获取ES中文档的数量
     * @Author 莫提
     * @Date 19:19 2020/10/10
     * @Param []
     * @return java.lang.Integer
     **/
    @Override
    public long getEsDocCount() {
        // 创建搜索请求
        SearchRequest searchRequest = new SearchRequest("moti-blog");
        // 创建搜索对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 设置查询条件
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        searchRequest.types("article").source(searchSourceBuilder);

        // 执行搜索
        SearchResponse response = null;
        try {
            response = client.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getHits().totalHits;
    }

    /**
     * @Description 删除全部回收站文章
     * @Author 莫提
     * @Date 20:55 2020/10/11
     * @Param []
     * @return java.lang.Integer
     **/
    @Override
    public Integer deleteTrash() {
        return articleMapper.deleteTrash();
    }

    /**
     * @Description 根据状态获取文章
     * @Author 莫提
     * @Date 8:52 2020/10/12
     * @Param [status]
     * @return java.util.List<com.moti.entity.Article>
     **/
    @Override
    public List<Article> queryByStatus(Integer status) {
        List<Article> articles = articleMapper.queryByStatus(status);
        articles.forEach(article -> {
            // 获取该文章的分类
            Kind kinds = articleKindMapper.queryByArticleId(article.getId());
            // 获取该文章的分类
            List<Tag> tags = articleTagMapper.queryByArticleId(article.getId());
            article.setKinds(kinds);
            article.setTags(tags);
        });
        return articles;
    }

    /**
     * @return java.util.List<com.moti.entity.Tag>
     * @Description 处理标签
     * @Author 莫提
     * @Date 9:06 2020/9/9
     * @Param [tags]
     **/
    public List<Tag> dealTag(String tags) {
        String[] strings = tags.split(";");
        List<Tag> list = new ArrayList<>();
        for (String s : strings) {
            if (!"".equals(s)) {
                list.add(new Tag(s));
            }
        }
        return list;
    }

    /**
     * @return java.util.List<com.moti.entity.Article>
     * @Description 处理文章集合
     * @Author 莫提
     * @Date 14:13 2020/10/6
     * @Param [articles]
     **/
    private List<Article> getArticles(List<Article> articles) {
        articles.forEach(article -> {
            List<Tag> list = articleTagMapper.queryByArticleId(article.getId());
            article.setTags(list);
            // 获取该文章的分类
            Kind kinds = articleKindMapper.queryByArticleId(article.getId());
            article.setKinds(kinds);
        });
        return articles;
    }

}