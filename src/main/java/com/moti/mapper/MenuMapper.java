package com.moti.mapper;

import com.moti.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * @InterfaceName MenuMapper
 * @Description (Menu)表数据库访问层
 * @author 莫提
 * @date 2020-09-21 14:13:23
 * @Version 1.0
 **/
@Mapper
public interface MenuMapper {

    /**
     * @Description 添加Menu
     * @author 莫提
     * @date 2020-09-21 14:13:23
     * @param menu 实例对象
     * @return 影响行数
     */
    int insert(Menu menu);
    
    /**
     * @Description 删除Menu
     * @author 莫提
     * @date 2020-09-21 14:13:23
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-21 14:13:23
     * @param id 主键
     * @return 实例对象
     */
    Menu queryById(Integer id);

    /**
     * @Description 查询全部数据
     * @author 莫提
     * @date 2020-09-21 14:13:23
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Menu> queryAll();

    /**
     * @Description 实体作为筛选条件查询数据
     * @author 莫提
     * @date 2020-09-21 14:13:23
     * @param menu 实例对象
     * @return 对象列表
     */
    List<Menu> queryAll(Menu menu);

    /**
     * @Description 修改Menu,根据 menu 的主键修改数据
     * @author 莫提
     * @date 2020-09-21 14:13:23
     * @param menu
     * @return 影响行数
     */
    int update(Menu menu);

}