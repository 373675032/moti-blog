package com.moti.mapper;

import com.moti.entity.Front;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

 /**
 * @InterfaceName FrontMapper
 * @Description (Front)表数据库访问层
 * @author 莫提
 * @date 2020-09-21 10:22:27
 * @Version 1.0
 **/
@Mapper
public interface FrontMapper {

    /**
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-09-21 10:22:27
     * @param id 主键
     * @return 实例对象
     */
    Front queryById(Integer id);

    /**
     * @Description 修改Front,根据 front 的主键修改数据
     * @author 莫提
     * @date 2020-09-21 10:22:27
     * @param front
     * @return 影响行数
     */
    int update(Front front);

}