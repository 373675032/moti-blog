package com.moti.mapper;

import com.moti.entity.ArticleKind;
import com.moti.entity.Kind;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 莫提
 * @InterfaceName ArticleKindMapper
 * @Description (ArticleKind)表数据库访问层
 * @date 2020-09-09 09:36:47
 * @Version 1.0
 **/
@Mapper
public interface ArticleKindMapper {

    /**
     * @param articleKind 实例对象
     * @return 影响行数
     * @Description 添加ArticleKind
     * @author 莫提
     * @date 2020-09-09 09:36:47
     */
    int insert(ArticleKind articleKind);

    /**
     * @param articleId 主键
     * @return 影响行数
     * @Description 删除ArticleKind
     * @author 莫提
     * @date 2020-09-09 09:36:47
     */
    int deleteById(Integer articleId);

    /**
     * @return java.lang.Integer
     * @Description 根据文集ID获取关联文章的数量
     * @Author 莫提
     * @Date 10:10 2020/9/9
     * @Param [id]
     **/
    Integer getArticleCount(Integer id);

    /**
     * @return java.util.List<com.moti.entity.Kind>
     * @Description 根据文章ID查询所有分类
     * @Author 莫提
     * @Date 20:28 2020/9/2
     * @Param [id]
     **/
    Kind queryByArticleId(Integer id);

}