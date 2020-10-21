package com.moti.service.impl;

import com.moti.entity.Front;
import com.moti.redis.AdminKey;
import com.moti.service.BaseService;
import com.moti.service.FrontService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 莫提
 * @ClassName FrontServiceImpl
 * @Description (Front)表服务实现类
 * @date 2020-09-21 10:22:27
 * @Version 1.0
 **/
@Service("frontService")
public class FrontServiceImpl extends BaseService implements FrontService {

    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-21 10:22:27
     */
    @Override
    public Front queryById(Integer id) {
        Front front = null;
        if (redisService.exists(AdminKey.getByFront,String.valueOf(id))) {
            front = redisService.get(AdminKey.getByFront, String.valueOf(id), Front.class);
        }else {
            front = frontMapper.queryById(id);
            redisService.set(AdminKey.getByFront, String.valueOf(id),front,60*60*24);
        }
        return front;
    }

    /**
     * @param front 实例对象
     * @return 是否成功
     * @Description 修改数据，哪个属性不为空就修改哪个属性
     * @author 莫提
     * @date 2020-09-21 10:22:27
     */
    @Override
    public boolean update(Front front) {
        if (frontMapper.update(front) == 1) {
            return true;
        }
        return false;
    }

}