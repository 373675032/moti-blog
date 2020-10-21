package com.moti.service;

import com.moti.entity.Kind;
import java.util.List;

/**
 * @InterfaceName KindService
 * @Description (Kind)表服务接口
 * @author 莫提
 * @date 2020-09-02 20:10:48
 * @Version 1.0
 **/
public interface KindService {

    /**
     * @Description 添加Kind
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * @param kind 实例对象
     * @return 是否成功
     */
    boolean insert(Kind kind);

    /**
     * @Description 删除Kind
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * @param id 主键
     * @return 实例对象
     */
    Kind queryById(Integer id);

    /**
     * @Description 查询全部数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Kind> queryAll();

    /**
     * @Description 实体作为筛选条件查询数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * @param kind 实例对象
     * @return 对象列表
     */
    List<Kind> queryAll(Kind kind);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * @param kind 实例对象
     * @return 是否成功
     */
    boolean update(Kind kind);

    /**
     * @Description 根据文章ID查询所有分类
     * @Author 莫提
     * @Date 20:28 2020/9/2
     * @Param [id]
     * @return java.util.List<com.moti.entity.Kind>
     **/
    Kind queryByArticleId(Integer id);

    /**
     * @Description 根据文集ID获取关联文章的数量
     * @Author 莫提
     * @Date 10:10 2020/9/9
     * @Param [id]
     * @return java.lang.Integer
     **/
    Integer getArticleCount(Integer id);

    /**
     * @Description 获取评论的数量
     * @Author 莫提
     * @Date 18:00 2020/9/23
     * @Param []
     * @return java.lang.Integer
     **/
    Integer getCount();

}