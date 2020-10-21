package com.moti.mapper;

import com.moti.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

 /**
 * @InterfaceName CommentMapper
 * @Description (Comment)表数据库访问层
 * @author 莫提
 * @date 2020-09-21 19:25:55
 * @Version 1.0
 **/
@Mapper
public interface CommentMapper {

    /**
     * @Description 添加Comment
     * @author 莫提
     * @date 2020-09-21 19:25:55
     * @param comment 实例对象
     * @return 影响行数
     */
    int insert(Comment comment);
    
    /**
     * @Description 删除Comment
     * @author 莫提
     * @date 2020-09-21 19:25:55
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * @Description 删除Comment
     * @author 莫提
     * @date 2020-09-21 19:25:55
     * @param id 回复ID
     * @return 影响行数
     */
    int deleteByReplyId(Integer id);

    /**
     * @Description 删除Comment
     * @author 莫提
     * @date 2020-09-21 19:25:55
     * @param id 文章ID
     * @return 影响行数
     */
    int deleteByArticleId(Integer id);

    /**
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-21 19:25:55
     * @param id 主键
     * @return 实例对象
     */
    Comment queryById(Integer id);

    /**
     * @Description 实体作为筛选条件查询数据
     * @author 莫提
     * @date 2020-09-21 19:25:55
     * @param comment 实例对象
     * @return 对象列表
     */
    List<Comment> queryAll(Comment comment);

    /**
     * @Description 修改Comment,根据 comment 的主键修改数据
     * @author 莫提
     * @date 2020-09-21 19:25:55
     * @param comment
     * @return 影响行数
     */
    int update(Comment comment);

    /**
     * @Description 根据文章ID和类型获取文章
     * @Author 莫提
     * @Date 20:35 2020/9/21
     * @Param [aId, type]
     * @return java.util.List<com.moti.entity.Comment>
     **/
    List<Comment> queryByArticleIdAndType(@Param("aId") Integer aId, @Param("type") Integer type);

    /**
     * @Description 获取所有回复的文章
     * @Author 莫提
     * @Date 20:39 2020/9/21
     * @Param [id]
     * @return java.util.List<com.moti.entity.Comment>
     **/
    List<Comment> queryReplyComment(Integer id);

    /**
     * @Description 获取评论的数量
     * @Author 莫提
     * @Date 18:00 2020/9/23
     * @Param []
     * @return java.lang.Integer
     **/
    Integer getCount();

    /**
     * @Description 获取未读评论的数量
     * @Author 莫提
     * @Date 10:58 2020/10/6
     * @Param []
     * @return java.lang.Integer
     **/
    Integer getUnReadCount();
 }