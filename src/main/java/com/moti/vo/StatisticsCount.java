package com.moti.vo;

/**
 * @ClassName: StatisticsCount
 * @Description: TODO
 * @author: 莫提
 * @date 2020/10/5 22:55
 * @Version: 1.0
 **/
public class StatisticsCount {
    Integer requests;
    Integer visitor;

    public StatisticsCount() {
    }

    public Integer getRequests() {
        return requests;
    }

    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    public Integer getVisitor() {
        return visitor;
    }

    public void setVisitor(Integer visitor) {
        this.visitor = visitor;
    }

    @Override
    public String toString() {
        return "StatisticsCount{" +
                "requests=" + requests +
                ", visitor=" + visitor +
                '}';
    }

    public StatisticsCount(Integer requests, Integer visitor) {
        this.requests = requests;
        this.visitor = visitor;
    }
}
