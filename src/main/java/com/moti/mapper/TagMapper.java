package com.moti.mapper;

import com.moti.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * @InterfaceName TagMapper
 * @Description (Tag)表数据库访问层
 * @author 莫提
 * @date 2020-09-02 20:10:48
 * @Version 1.0
 **/
@Mapper
public interface TagMapper {

    /**
     * @Description 添加Tag
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * @param tag 实例对象
     * @return 影响行数
     */
    int insert(Tag tag);
    
    /**
     * @Description 删除Tag
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
    Tag queryById(Integer id);

    /**
     * @Description 查询全部数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Tag> queryAll();

    /**
     * @Description 实体作为筛选条件查询数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * @param tag 实例对象
     * @return 对象列表
     */
    List<Tag> queryAll(Tag tag);

    /**
     * @Description 修改Tag,根据 tag 的主键修改数据
     * @author 莫提
     * @date 2020-09-02 20:10:48
     * @param tag
     * @return 影响行数
     */
    int update(Tag tag);

     /**
      * @Description 获取评论的数量
      * @Author 莫提
      * @Date 18:00 2020/9/23
      * @Param []
      * @return java.lang.Integer
      **/
     Integer getCount();
}