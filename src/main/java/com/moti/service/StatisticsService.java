package com.moti.service;

import com.moti.entity.Statistics;
import com.moti.vo.StatisticsCount;
import com.moti.vo.VisitStatistics;
import com.moti.vo.VisitorStatistics;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @InterfaceName StatisticsService
 * @Description (Statistics)表服务接口
 * @author 莫提
 * @date 2020-10-05 20:09:53
 * @Version 1.0
 **/
public interface StatisticsService {

    /**
     * @Description 添加Statistics
     * @author 莫提
     * @date 2020-10-05 20:09:53
     * @param statistics 实例对象
     * @return 是否成功
     */
    boolean insert(Statistics statistics);

    /**
     * @Description 删除Statistics
     * @author 莫提
     * @date 2020-10-05 20:09:53
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

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
     * @Description 更新访问数据，如果不存在就插入，如果存在就增加请求数
     * @author 莫提
     * @date 2020-10-05 16:35:42
     */
    void update(HttpServletRequest request, Integer aId);

    /**
     * @Description 根据IP地址获取今日访问数据
     * @Author 莫提
     * @Date 16:39 2020/10/5
     * @Param [ip]
     * @return com.moti.entity.Statistics
     **/
    Statistics findByIp(String ip,Integer aId);

    /**
     * @Description 添加IP今日的访问数次数
     * @Author 莫提
     * @Date 16:40 2020/10/5
     * @Param [ip]
     * @return void
     **/
    void addRequestCount(String ip,Integer aId);


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
     * @Description 获取访问数据
     * @Author 莫提
     * @Date 20:30 2020/10/5
     * @Param []
     * @return com.moti.vo.VisitStatistics
     **/
    VisitStatistics getVisitStatistics();

    /**
     * @Description 获取所有访客数据
     * @Author 莫提
     * @Date 22:26 2020/10/5
     * @Param []
     * @return java.util.List<com.moti.vo.VisitorStatistics>
     **/
    List<VisitorStatistics> getVisitorStatistics();

    /**
     * @return java.util.Map<java.lang.String, com.moti.vo.StatisticsCount>
     * @Description 获取统计数量信息
     * @Author 莫提
     * @Date 22:58 2020/10/5
     * @Param []
     **/
    Map<String, StatisticsCount> getStatisticsCount();

}