package com.awei.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author jiangwei
 * @create 2022-05-01 21:29
 */
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/user");
            return false;
        }
        System.out.println(handler);
        System.out.println(request.getSession().getAttribute("user"));
        return true;
    }
}
