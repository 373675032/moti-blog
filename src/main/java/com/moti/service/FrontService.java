package com.moti.service;

import com.moti.entity.Front;
import java.util.List;

/**
 * @InterfaceName FrontService
 * @Description (Front)表服务接口
 * @author 莫提
 * @date 2020-09-21 10:22:27
 * @Version 1.0
 **/
public interface FrontService {

    /**
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-21 10:22:27
     * @param id 主键
     * @return 实例对象
     */
    Front queryById(Integer id);

    /**
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-21 10:22:27
     * @param front 实例对象
     * @return 是否成功
     */
    boolean update(Front front);

}