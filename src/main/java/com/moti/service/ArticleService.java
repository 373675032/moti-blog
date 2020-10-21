package com.moti.service;

import com.moti.entity.Article;
import com.moti.entity.ArticleDateArchive;

import java.util.Date;
import java.util.List;

/**
 * @InterfaceName ArticleService
 * @Description (Article)表服务接口
 * @author 莫提
 * @date 2020-09-02 20:18:45
 * @Version 1.0
 **/
public interface ArticleService {

    /**
     * @Description 添加Article
     * @author 莫提
     * @date 2020-09-02 20:18:45
     * @param article 实例对象
     * @return 是否成功
     */
    boolean insert(Article article);

    /**
     * @Description 删除Article
     * @author 莫提
     * @date 2020-09-02 20:18:45
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-02 20:18:45
     * @param id 主键
     * @return 实例对象
     */
    Article queryById(Integer id);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-02 20:18:45
     * @param article 实例对象
     * @return 是否成功
     */
    boolean update(Article article);

    /**
     * @Description 根据条件获取文章数量
     * @Author 莫提
     * @Date 10:42 2020/9/3
     * @Param [article]
     * @return java.lang.Integer
     **/
    Integer totalCount(Integer status);

    /**
     * @Description 发布文章
     * @Author 莫提
     * @Date 9:04 2020/9/9
     * @Param [title, content, tags, kind]
     * @return boolean
     **/
    Integer publish(String title,String content,String tags,String kind,String introduce,Integer status);

    /**
     * @Description 更新文章
     * @Author 莫提
     * @Date 21:38 2020/10/10
     * @Param [id, title, content, tags, kind, introduce, status]
     * @return void
     **/
    boolean update(Integer id,String title,String content,String tags,String kind,String introduce,Integer status);

    /**
     * @Description 获取阅读量最高的文章
     * @Author 莫提
     * @Date 19:32 2020/9/14
     * @Param []
     * @return java.util.List<com.moti.entity.Article>
     **/
    List<Article> orderByReadCount();

    /**
     * @Description 获得文章日期归档
     * @Author 莫提
     * @Date 19:50 2020/9/14
     * @Param []
     * @return java.util.List<com.moti.entity.ArticleDateArchive>
     **/
    public List<ArticleDateArchive> getArchive();

    /**
     * @Description 根据发表时间降序获取文章
     * @Author 莫提
     * @Date 19:32 2020/9/14
     * @Param []
     * @return java.util.List<com.moti.entity.Article>
     **/
    List<Article> orderByPublishTime(Integer page);

    /**
     * @Description 阅读文章 ,阅读量加1
     * @Author 莫提
     * @Date 16:08 2020/9/20
     * @Param [id]
     * @return void
     **/
    void addReadCount(Integer id);

    /**
     * @Description 根据Id获取阅读量
     * @Author 莫提
     * @Date 12:58 2020/10/9
     * @Param [id]
     * @return java.lang.Integer
     **/
    Integer getReadCount(Integer id);

    /**
     * @Description 根据Id获得文章标题
     * @Author 莫提
     * @Date 9:30 2020/9/22
     * @Param [aId]
     * @return java.lang.String
     **/
    String getTitle(Integer aId);

    /**
     * @Description 根据标签ID获取已发布的文章
     * @Author 莫提
     * @Date 13:34 2020/10/6
     * @Param [id]
     * @return java.util.List<com.moti.entity.Article>
     **/
    List<Article> findByTagId(Integer id);

    /**
     * @Description 根据文集ID获取已发布的文章
     * @Author 莫提
     * @Date 13:34 2020/10/6
     * @Param [id]
     * @return java.util.List<com.moti.entity.Article>
     **/
    List<Article> findByKindId(Integer id);

    /**
     * @Description 根据日期归档获取文章
     * @Author 莫提
     * @Date 13:58 2020/10/6
     * @Param [date]
     * @return java.util.List<com.moti.entity.Article>
     **/
    List<Article> findByDate(Date date);

    /**
     * @Description 从ES中搜索
     * @Author 莫提
     * @Date 0:58 2020/10/10
     * @Param [content]
     * @return java.util.List<com.moti.entity.Article>
     **/
    List<Article> searchFromEs(String content);

    /**
     * @Description 初始化ES
     * @Author 莫提
     * @Date 19:09 2020/10/10
     * @Param []
     * @return void
     **/
    void initEs();

    /**
     * @Description 获取ES中文档的数量
     * @Author 莫提
     * @Date 19:19 2020/10/10
     * @Param []
     * @return java.lang.Integer
     **/
    long getEsDocCount();

    /**
     * @Description 删除全部回收站文章
     * @Author 莫提
     * @Date 20:55 2020/10/11
     * @Param []
     * @return java.lang.Integer
     **/
    Integer deleteTrash();

    /**
     * @Description 根据状态获取文章
     * @Author 莫提
     * @Date 8:45 2020/10/12
     * @Param [status]
     * @return java.util.List<com.moti.entity.Article>
     **/
    List<Article> queryByStatus(Integer status);
}