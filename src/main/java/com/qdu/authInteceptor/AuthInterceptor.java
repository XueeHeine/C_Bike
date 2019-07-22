package com.qdu.authInteceptor;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alpha-LGC on 2018/11/8.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if(request.getRequestURI().equals(request.getContextPath()+"/")) {//不烂首页
            return true;
        }
        if(request.getRequestURI().equals(request.getContextPath()+"/emp/empAddAndUpdate")) {//不拦注册
            return true;
        }
        if(request.getRequestURI().equals(request.getContextPath()+"/login/login")) {//不拦注册县官
            return true;
        }
        if(request.getRequestURI().equals(request.getContextPath()+"/login/logout")) {//不拦注册县官
            return true;
        }
        if(request.getRequestURI().equals(request.getContextPath()+"/user/addOrUpdate")) {//不拦添加
            return true;
        }
        if(request.getSession().getAttribute("user")!=null){//不拦
            return true;
        }
        response.sendRedirect(request.getContextPath()+"/pages/login.jsp");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
