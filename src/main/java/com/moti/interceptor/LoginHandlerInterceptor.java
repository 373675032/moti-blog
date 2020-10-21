package com.moti.interceptor;

import com.moti.entity.Admin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: LoginHandlerInterceptor
 * @Description: 登录拦截器:用于登录检查,权限控制
 * @author: 莫提
 * @date 2020/10/6 15:16
 * @Version: 1.0
 **/
public class LoginHandlerInterceptor implements HandlerInterceptor {

    /**
     * 在目标方式执行之前执行
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null){
            //未登录,返回登录页面
            response.sendRedirect("/moti-blog/error401Page");
            return false;
        }else {
            //已登录,放行
            return true;
        }
    }
}
