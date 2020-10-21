package com.moti.service;

import com.moti.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 莫提
 * @InterfaceName CommentService
 * @Description (Comment)表服务接口
 * @date 2020-09-21 19:25:47
 * @Version 1.0
 **/
public interface CommentService {

    /**
     * @param comment 实例对象
     * @return 是否成功
     * @Description 添加Comment
     * @author 莫提
     * @date 2020-09-21 19:25:47
     */
    boolean insert(Comment comment);

    /**
     * @param id 主键
     * @return 是否成功
     * @Description 删除Comment
     * @author 莫提
     * @date 2020-09-21 19:25:47
     */
    boolean deleteById(Integer id);

    /**
     * @param id 回复ID
     * @return 影响行数
     * @Description 删除Comment
     * @author 莫提
     * @date 2020-09-21 19:25:55
     */
    boolean deleteByReplyId(Integer id);


    /**
     * @Description 删除Comment
     * @author 莫提
     * @date 2020-09-21 19:25:55
     * @param id 文章ID
     * @return 影响行数
     */
    boolean deleteByArticleId(Integer id);

    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-21 19:25:47
     */
    Comment queryById(Integer id);

    /**
     * @param comment 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     * @author 莫提
     * @date 2020-09-21 19:25:47
     */
    List<Comment> queryAll(Comment comment);

    /**
     * @param comment 实例对象
     * @return 是否成功
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-21 19:25:47
     */
    boolean update(Comment comment);

    /**
     * @return java.util.List<com.moti.entity.Comment>
     * @Description 根据文章ID和类型获取文章
     * @Author 莫提
     * @Date 20:35 2020/9/21
     * @Param [aId, type]
     **/
    List<Comment> queryByArticleIdAndType(Integer aId, Integer type);

    /**
     * @return java.lang.Integer
     * @Description 获取评论的数量
     * @Author 莫提
     * @Date 18:00 2020/9/23
     * @Param []
     **/
    Integer getCount();

    /**
     * @return java.lang.Integer
     * @Description 获取未读评论的数量
     * @Author 莫提
     * @Date 10:58 2020/10/6
     * @Param []
     **/
    Integer getUnReadCount();
}