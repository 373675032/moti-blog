package com.moti.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @ClassName: LogUtils
 * @Description: 打印日志工具类
 * @author: 莫提
 * @date 2020/9/21 20:13
 * @Version: 1.0
 **/
public class LogUtils {
    private static Logger logger;
    public static Logger getInstance(Class c){
        return logger =  LoggerFactory.getLogger(c);
    }
    private LogUtils(){}
}
