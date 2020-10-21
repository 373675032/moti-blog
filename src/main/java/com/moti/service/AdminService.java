package com.moti.service;

import com.moti.entity.Admin;
import java.util.List;

/**
 * @InterfaceName AdminService
 * @Description (Admin)表服务接口
 * @author 莫提
 * @date 2020-08-30 22:34:34
 * @Version 1.0
 **/
public interface AdminService {

    /**
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-08-30 22:34:34
     * @param id 主键
     * @return 实例对象
     */
    Admin queryById(Integer id);


    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-08-30 22:34:34
     * @param admin 实例对象
     * @return 是否成功
     */
    boolean update(Admin admin);

}