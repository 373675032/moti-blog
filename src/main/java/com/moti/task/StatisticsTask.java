package com.moti.task;

import com.moti.entity.Statistics;
import com.moti.redis.StatisticsKey;
import com.moti.service.BaseService;
import com.moti.utils.LogUtils;
import org.slf4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Set;

/**
 * @ClassName: StatisticsTask
 * @Description: 关于统计的定时器任务
 * @author: 莫提
 * @date 2020/10/9 14:55
 * @Version: 1.0
 **/
@Service
public class StatisticsTask extends BaseService {

    private Logger logger = LogUtils.getInstance(StatisticsTask.class);

    /**
     * @Description 定时任务统计访客信息：每天12：00保存前一日的统计数据
     * @Author 莫提
     * @Date 14:57 2020/10/9
     * @Param []
     * @return void
     **/
    @Scheduled(cron = "0 0 0 * * ?")
    public void saveRequests(){
        logger.warn("【定时任务】开始统计访客信息");
        long startTime = System.currentTimeMillis();
        Set<String> keys = redisService.keys(StatisticsKey.todayVisitor);
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,-24);
        for (String key : keys) {
            String[] strings = key.split("visitor:");
            String str = strings[1];
            String[] split = str.split(":");
            String ip = split[0];
            String id = split[1];
            Integer count = redisService.get(StatisticsKey.todayVisitor, str, Integer.class);
            Statistics statistics = new Statistics(ip,count,calendar.getTime(),Integer.valueOf(id));
            statisticsMapper.insert(statistics);
            redisService.del(StatisticsKey.todayVisitor, str);
        }
        long endTime = System.currentTimeMillis();
        logger.warn("【定时任务】结束统计访客信息，"+"程序运行时间：" + (endTime - startTime) + "ms");
    }
}
