package com.moti.mapper;

import com.moti.entity.Statistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

 /**
 * @InterfaceName StatisticsMapper
 * @Description (Statistics)表数据库访问层
 * @author 莫提
 * @date 2020-10-05 20:09:53
 * @Version 1.0
 **/
@Mapper
public interface StatisticsMapper {

    /**
     * @Description 添加Statistics
     * @author 莫提
     * @date 2020-10-05 20:09:53
     * @param statistics 实例对象
     * @return 影响行数
     */
    int insert(Statistics statistics);
    
    /**
     * @Description 删除Statistics
     * @author 莫提
     * @date 2020-10-05 20:09:53
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * @Description 查询单条数据
     * @author 莫提
     * @date 2020-10-05 20:09:53
     * @param id 主键
     * @return 实例对象
     */
    Statistics queryById(Integer id);

    /**
     * @Description 查询全部数据
     * @author 莫提
     * @date 2020-10-05 20:09:53
     * 分页使用MyBatis的插件实现
     * @return 对象列表
     */
    List<Statistics> queryAll();

    /**
     * @Description 实体作为筛选条件查询数据
     * @author 莫提
     * @date 2020-10-05 20:09:53
     * @param statistics 实例对象
     * @return 对象列表
     */
    List<Statistics> queryAll(Statistics statistics);

    /**
     * @Description 修改Statistics,根据 statistics 的主键修改数据
     * @author 莫提
     * @date 2020-10-05 20:09:53
     * @param statistics
     * @return 影响行数
     */
    int update(Statistics statistics);

     /**
      * @Description 根据IP地址获取今日访问数据
      * @Author 莫提
      * @Date 16:39 2020/10/5
      * @Param [ip]
      * @return com.moti.entity.Statistics
      **/
     Statistics findByIp(@Param("ip") String ip, @Param("aId")Integer aId);

     /**
      * @Description 添加IP今日的访问数次数
      * @Author 莫提
      * @Date 16:40 2020/10/5
      * @Param [ip]
      * @return void
      **/
     void addRequestCount(@Param("ip") String ip,@Param("aId")Integer aId);

     /**
      * @Description 获取从昨天开始近10天的日期 
      * @Author 莫提
      * @Date 20:23 2020/10/5
      * @Param []
      * @return java.util.List<java.util.Date>
      **/
     List<Date> getRecentDates();
     
     /**
      * @Description 获取从昨天开始近10天每天的访问量
      * @Author 莫提
      * @Date 20:24 2020/10/5
      * @Param []
      * @return java.util.List<java.lang.Integer>
      **/
     List<Integer> getRecentRequests();
     
     /**
      * @Description 获取从昨天开始近10天每天的访客数
      * @Author 莫提
      * @Date 20:24 2020/10/5
      * @Param []
      * @return java.util.List<java.lang.Integer>
      **/
     List<Integer> getRecentVisitors();

     /**
      * @Description 获取不同时间段的访问数量
      * @Author 莫提
      * @Date 23:04 2020/10/5
      * @Param [type]
      * @return Integer
      **/
     Integer getRequestCount(Integer type);

     /**
      * @Description 获取不同时间段的访客数量
      * @Author 莫提
      * @Date 23:04 2020/10/5
      * @Param [type]
      * @return Integer
      **/
     Integer getVisitorCount(Integer type);
}