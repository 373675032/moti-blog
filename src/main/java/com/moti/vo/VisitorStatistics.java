package com.moti.vo;

import com.moti.entity.Statistics;

/**
 * @ClassName: VisitorStatistics
 * @Description: TODO
 * @author: 莫提
 * @date 2020/10/5 22:23
 * @Version: 1.0
 **/
public class VisitorStatistics {
    String target;
    Statistics statistics;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public VisitorStatistics() {
    }

    public VisitorStatistics(String target, Statistics statistics) {
        this.target = target;
        this.statistics = statistics;
    }

    @Override
    public String toString() {
        return "VisitorStatistics{" +
                "target='" + target + '\'' +
                ", statistics=" + statistics +
                '}';
    }
}
