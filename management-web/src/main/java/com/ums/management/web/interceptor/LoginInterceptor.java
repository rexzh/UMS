package com.ums.management.web.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ums.management.web.utility.UserExtension;
import com.ums.management.core.view.model.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter {

    private final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);
    private static final String LOGIN_PAGE = "/login.html";


    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String requestUri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String url = requestUri.substring(contextPath.length());
        String method = request.getMethod();
        /*
        System.out.println("requestUri:" + requestUri);
        System.out.println("contextPath:" + contextPath);
        System.out.println("url:" + url);
        System.out.println("method:" + method);
        */
        if(url.startsWith("/sysConfig.json") && method.equals("GET"))
            return true;

        UserVO loginUser = UserExtension.getCurrentUser(request.getSession());
        if (loginUser == null) {
            
            response.setStatus(401);
            return false;
        } else
            return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        /*
        log.info("==============执行顺序: 2、postHandle================");
        if (modelAndView != null) {  //加入当前时间
            modelAndView.addObject("var", "测试postHandle");
        }
        */
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        /*
        log.info("==============执行顺序: 3、afterCompletion================");
        */
    }
}    
