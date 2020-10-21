package com.moti.service;

import com.moti.entity.Tag;

import java.util.List;

/**
 * @author 莫提
 * @InterfaceName TagService
 * @Description (Tag)表服务接口
 * @date 2020-09-02 20:10:48
 * @Version 1.0
 **/
public interface TagService {

    /**
     * @param tag 实例对象
     * @return 是否成功
     * @Description 添加Tag
     * @author 莫提
     * @date 2020-09-02 20:10:48
     */
    boolean insert(Tag tag);

    /**
     * @param id 主键
     * @return 是否成功
     * @Description 删除Tag
     * @author 莫提
     * @date 2020-09-02 20:10:48
     */
    boolean deleteById(Integer id);

    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     */
    Tag queryById(Integer id);

    /**
     * @return 对象列表
     * @Description 查询全部数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * 分页使用MyBatis的插件实现
     */
    List<Tag> queryAll();

    /**
     * @param tag 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     */
    List<Tag> queryAll(Tag tag);

    /**
     * @param tag 实例对象
     * @return 是否成功
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-02 20:10:48
     */
    boolean update(Tag tag);

    /**
     * @return java.util.List<com.moti.entity.Tag>
     * @Description 根据文章ID查询所有标签
     * @Author 莫提
     * @Date 20:28 2020/9/2
     * @Param [id]
     **/
    List<Tag> queryByArticleId(Integer id);

    /**
     * @return java.lang.Integer
     * @Description 根据标签ID获取关联文章的数量
     * @Author 莫提
     * @Date 10:10 2020/9/9
     * @Param [id]
     **/
    Integer getArticleCount(Integer id);

    /**
     * @return java.lang.Integer
     * @Description 获取评论的数量
     * @Author 莫提
     * @Date 18:00 2020/9/23
     * @Param []
     **/
    Integer getCount();

}