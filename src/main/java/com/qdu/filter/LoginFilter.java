package com.qdu.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Alpha-LGC on 2019/4/10.
 */

@Component
@WebFilter(urlPatterns = "/**")
public class LoginFilter implements Filter {
    private String redirectUrl = "/login/logout";
    private String sessionKey = "USER_SESSION";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse rep = (HttpServletResponse) response;

      // HttpSession session = req.getSession();
        Session session = SecurityUtils.getSubject().getSession();

        if(req.getRequestURI().contains("haveSession") ){


            if(session.getAttribute(sessionKey) == null) {
                //如果判断是 AJAX 请求,直接设置为session超时
                if (req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equals("XMLHttpRequest")) {

                    rep.setHeader("sessionstatus", "timeout");




                    return;
                }
            }

        }
            filterChain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
