package com.moti.service.impl;

import com.moti.entity.Comment;
import com.moti.mapper.CommentMapper;
import com.moti.redis.CommentKey;
import com.moti.service.BaseService;
import com.moti.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莫提
 * @ClassName CommentServiceImpl
 * @Description (Comment)表服务实现类
 * @date 2020-09-21 19:25:47
 * @Version 1.0
 **/
@Service("commentService")
public class CommentServiceImpl extends BaseService implements CommentService {

    /**
     * @param comment 实例对象
     * @return 是否成功
     * @Description 添加Comment
     * @author 莫提
     * @date 2020-09-21 19:25:47
     */
    @Override
    public boolean insert(Comment comment) {
        if (commentMapper.insert(comment) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @param id 主键
     * @return 是否成功
     * @Description 删除Comment
     * @author 莫提
     * @date 2020-09-21 19:25:47
     */
    @Override
    public boolean deleteById(Integer id) {
        if (commentMapper.deleteById(id) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @param id 回复ID
     * @return 影响行数
     * @Description 删除Comment
     * @author 莫提
     * @date 2020-09-21 19:25:55
     */
    @Override
    public boolean deleteByReplyId(Integer id) {
        if (commentMapper.deleteByReplyId(id) > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param id 文章ID
     * @return 影响行数
     * @Description 删除Comment
     * @author 莫提
     * @date 2020-09-21 19:25:55
     */
    @Override
    public boolean deleteByArticleId(Integer id) {
        if (commentMapper.deleteByArticleId(id) > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-21 19:25:47
     */
    @Override
    public Comment queryById(Integer id) {
        Comment comment = commentMapper.queryById(id);
        comment.setTitle(articleMapper.getTitle(comment.getArticleId()));
        List<Comment> list = commentMapper.queryReplyComment(comment.getId());
        comment.setReply(list);
        return comment;
    }

    /**
     * @param comment 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     * @author 莫提
     * @date 2020-09-21 19:25:47
     */
    @Override
    public List<Comment> queryAll(Comment comment) {
        List<Comment> comments = commentMapper.queryAll(comment);
        // 获取所有回复
        comments.forEach(temp -> {
            Comment search = new Comment();
            search.setReplyId(temp.getId());
            List<Comment> list = commentMapper.queryAll(search);
            temp.setReply(list);
        });
        return comments;
    }

    /**
     * @param comment 实例对象
     * @return 是否成功
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-21 19:25:47
     */
    @Override
    public boolean update(Comment comment) {
        if (commentMapper.update(comment) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @return java.util.List<com.moti.entity.Comment>
     * @Description 根据文章ID和类型获取文章
     * @Author 莫提
     * @Date 20:35 2020/9/21
     * @Param [aId, type]
     **/
    @Override
    public List<Comment> queryByArticleIdAndType(Integer aId, Integer type) {
        List<Comment> comments = null;
        if (redisService.exists(CommentKey.getByArticleId, String.valueOf(aId))) {
            comments = redisService.getList(CommentKey.getByArticleId, String.valueOf(aId), Comment.class);
        } else {
            comments = commentMapper.queryByArticleIdAndType(aId, type);
            comments.forEach(comment -> {
                List<Comment> list = commentMapper.queryReplyComment(comment.getId());
                comment.setReply(list);
            });
            redisService.setList(CommentKey.getByArticleId, String.valueOf(aId), comments);
        }
        return comments;
    }

    /**
     * @return java.lang.Integer
     * @Description 获取评论的数量
     * @Author 莫提
     * @Date 18:00 2020/9/23
     * @Param []
     **/
    @Override
    public Integer getCount() {
        return commentMapper.getCount();
    }

    /**
     * @return java.lang.Integer
     * @Description 获取未读评论的数量
     * @Author 莫提
     * @Date 10:58 2020/10/6
     * @Param []
     **/
    @Override
    public Integer getUnReadCount() {
        return commentMapper.getUnReadCount();
    }

}