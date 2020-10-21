package com.moti.service;

import com.moti.entity.Link;
import java.util.List;

/**
 * @InterfaceName LinkService
 * @Description (Link)表服务接口
 * @author 莫提
 * @date 2020-09-21 15:01:32
 * @Version 1.0
 **/
public interface LinkService {

    /**
     * @Description 添加Link
     * @author 莫提
     * @date 2020-09-21 15:01:32
     * @param link 实例对象
     * @return 是否成功
     */
    boolean insert(Link link);

    /**
     * @Description 删除Link
     * @author 莫提
     * @date 2020-09-21 15:01:32
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-21 15:01:32
     * @param id 主键
     * @return 实例对象
     */
    Link queryById(Integer id);

    /**
     * @Description 查询全部数据
     * @author 莫提
     * @date 2020-09-21 15:01:32
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Link> queryAll();

    /**
     * @Description 实体作为筛选条件查询数据
     * @author 莫提
     * @date 2020-09-21 15:01:32
     * @param link 实例对象
     * @return 对象列表
     */
    List<Link> queryAll(Link link);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-21 15:01:32
     * @param link 实例对象
     * @return 是否成功
     */
    boolean update(Link link);

}