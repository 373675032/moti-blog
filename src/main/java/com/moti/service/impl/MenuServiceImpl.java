package com.moti.service.impl;

import com.moti.entity.Menu;
import com.moti.mapper.MenuMapper;
import com.moti.redis.MenuKey;
import com.moti.service.BaseService;
import com.moti.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 莫提
 * @ClassName MenuServiceImpl
 * @Description (Menu)表服务实现类
 * @date 2020-09-21 14:13:23
 * @Version 1.0
 **/
@Service("menuService")
public class MenuServiceImpl extends BaseService implements MenuService {

    /**
     * @param menu 实例对象
     * @return 是否成功
     * @Description 添加Menu
     * @author 莫提
     * @date 2020-09-21 14:13:23
     */
    @Override
    public boolean insert(Menu menu) {
        if (menuMapper.insert(menu) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @param id 主键
     * @return 是否成功
     * @Description 删除Menu
     * @author 莫提
     * @date 2020-09-21 14:13:23
     */
    @Override
    public boolean deleteById(Integer id) {
        if (menuMapper.deleteById(id) == 1) {
            return true;
        }
        return false;
    }

    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-21 14:13:23
     */
    @Override
    public Menu queryById(Integer id) {
        return menuMapper.queryById(id);
    }

    /**
     * @return 对象列表
     * @Description 查询全部数据
     * @author 莫提
     * @date 2020-09-21 14:13:23
     * 分页使用MyBatis的插件实现
     */
    @Override
    public List<Menu> queryAll() {
        return menuMapper.queryAll();
    }

    /**
     * @param menu 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     * @author 莫提
     * @date 2020-09-21 14:13:23
     */
    @Override
    public List<Menu> queryAll(Menu menu) {
        return menuMapper.queryAll(menu);
    }

    /**
     * @Description 获取左侧菜单
     * @Author 莫提
     * @Date 12:18 2020/10/9
     * @Param []
     * @return java.util.List<com.moti.entity.Menu>
     **/
    @Override
    public List<Menu> getLeftMenus() {
        // 获取缓存中左侧菜单
        List<Menu> leftMenus = null;
        if (redisService.exists(MenuKey.getLeftMenu,"")) {
            leftMenus = redisService.getList(MenuKey.getLeftMenu, "", Menu.class);
        }else {
            leftMenus = menuMapper.queryAll(new Menu(0));
            // 获取并存入缓存
            redisService.setList(MenuKey.getLeftMenu,"",leftMenus);
        }
        return leftMenus;
    }

    /**
     * @Description 获取右侧菜单
     * @Author 莫提
     * @Date 12:18 2020/10/9
     * @Param []
     * @return java.util.List<com.moti.entity.Menu>
     **/
    @Override
    public List<Menu> getRightMenus() {
        // 获取缓存中右侧菜单
        List<Menu> rightMenus = null;
        if (redisService.exists(MenuKey.getRightMenu,"")) {
            rightMenus = redisService.getList(MenuKey.getRightMenu, "", Menu.class);
        }else {
            rightMenus = menuMapper.queryAll(new Menu(1));
            // 获取并存入缓存
            redisService.setList(MenuKey.getRightMenu,"",rightMenus);
        }
        return rightMenus;
    }

    /**
     * @param menu 实例对象
     * @return 是否成功
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-21 14:13:23
     */
    @Override
    public boolean update(Menu menu) {
        if (menuMapper.update(menu) == 1) {
            return true;
        }
        return false;
    }

}