package com.moti.mapper;

import com.moti.entity.Article;
import com.moti.entity.ArticleDateArchive;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

 /**
 * @InterfaceName ArticleMapper
 * @Description (Article)表数据库访问层
 * @author 莫提
 * @date 2020-09-02 20:18:45
 * @Version 1.0
 **/
@Mapper
public interface ArticleMapper {

    /**
     * @Description 添加Article
     * @author 莫提
     * @date 2020-09-02 20:18:45
     * @param article 实例对象
     * @return 影响行数
     */
    int insert(Article article);
    
    /**
     * @Description 删除Article
     * @author 莫提
     * @date 2020-09-02 20:18:45
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-02 20:18:45
     * @param id 主键
     * @return 实例对象
     */
    Article queryById(Integer id);

    /**
     * @Description 根据状态获取文章
     * @Author 莫提
     * @Date 8:45 2020/10/12
     * @Param [status]
     * @return java.util.List<com.moti.entity.Article>
     **/
    List<Article> queryByStatus(Integer status);

    /**
     * @Description 修改Article,根据 article 的主键修改数据
     * @author 莫提
     * @date 2020-09-02 20:18:45
     * @param article
     * @return 影响行数
     */
    int update(Article article);

    /**
      * @Description 根据条件获取文章数量
      * @Author 莫提
      * @Date 10:42 2020/9/3
      * @Param [article]
      * @return java.lang.Integer
      **/
    Integer totalCount(Integer status);

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
     List<ArticleDateArchive> getArchive();

     /**
      * @Description 根据发表时间降序获取文章
      * @Author 莫提
      * @Date 19:32 2020/9/14
      * @Param []
      * @return java.util.List<com.moti.entity.Article>
      **/
     List<Article> orderByPublishTime();

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
      * @Description 删除全部回收站文章
      * @Author 莫提
      * @Date 20:55 2020/10/11
      * @Param []
      * @return java.lang.Integer
      **/
     Integer deleteTrash();
}