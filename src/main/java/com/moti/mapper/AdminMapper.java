package com.moti.mapper;

import com.moti.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * @InterfaceName AdminMapper
 * @Description (Admin)表数据库访问层
 * @author 莫提
 * @date 2020-08-30 22:34:34
 * @Version 1.0
 **/
@Mapper
public interface AdminMapper {

    /**
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-08-30 22:34:34
     * @param id 主键
     * @return 实例对象
     */
    Admin queryById(Integer id);

    /**
     * @Description 修改Admin,根据 admin 的主键修改数据
     * @author 莫提
     * @date 2020-08-30 22:34:34
     * @param admin
     * @return 影响行数
     */
    int update(Admin admin);

}