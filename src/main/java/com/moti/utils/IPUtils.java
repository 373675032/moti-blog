package com.moti.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName: IPUtils
 * @Description: IP工具类
 * @author: 莫提
 * @date 2020/9/21 20:13
 * @Version: 1.0
 **/
public class IPUtils {

    /**
     * 获取用户的真实IP
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
