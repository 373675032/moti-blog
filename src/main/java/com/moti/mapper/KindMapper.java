package com.moti.mapper;

import com.moti.entity.Kind;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * @InterfaceName KindMapper
 * @Description (Kind)表数据库访问层
 * @author 莫提
 * @date 2020-09-02 20:10:48
 * @Version 1.0
 **/
@Mapper
public interface KindMapper {

    /**
     * @Description 添加Kind
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * @param kind 实例对象
     * @return 影响行数
     */
    int insert(Kind kind);
    
    /**
     * @Description 删除Kind
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

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
     * @Description 修改Kind,根据 kind 的主键修改数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * @param kind
     * @return 影响行数
     */
    int update(Kind kind);


     /**
      * @Description 获取评论的数量
      * @Author 莫提
      * @Date 18:00 2020/9/23
      * @Param []
      * @return java.lang.Integer
      **/
     Integer getCount();
}