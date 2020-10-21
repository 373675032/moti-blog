package com.moti.service;

import com.moti.entity.Link;
import com.moti.entity.Menu;
import java.util.List;

/**
 * @InterfaceName MenuService
 * @Description (Menu)表服务接口
 * @author 莫提
 * @date 2020-09-21 14:13:23
 * @Version 1.0
 **/
public interface MenuService {

    /**
     * @Description 添加Menu
     * @author 莫提
     * @date 2020-09-21 14:13:23
     * @param menu 实例对象
     * @return 是否成功
     */
    boolean insert(Menu menu);

    /**
     * @Description 删除Menu
     * @author 莫提
     * @date 2020-09-21 14:13:23
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

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
     * @Description 获取左侧菜单
     * @Author 莫提
     * @Date 12:18 2020/10/9
     * @Param []
     * @return java.util.List<com.moti.entity.Menu>
     **/
    List<Menu> getLeftMenus();

    /**
     * @Description 获取右侧菜单
     * @Author 莫提
     * @Date 12:18 2020/10/9
     * @Param []
     * @return java.util.List<com.moti.entity.Menu>
     **/
    List<Menu> getRightMenus();

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-21 14:13:23
     * @param menu 实例对象
     * @return 是否成功
     */
    boolean update(Menu menu);

}