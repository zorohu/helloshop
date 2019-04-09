package com.zero.my.shop.web.ui.Interceptor;

import com.zero.my.shop.web.ui.constant.SystemConstants;
import com.zero.my.shop.web.ui.dto.TbUserDto;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * @classname: LoginInterceptor
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-31 14:39
 **/
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        TbUserDto tbUser = (TbUserDto) httpServletRequest.getSession().getAttribute(SystemConstants.SESSION_USER_KEY);

        //未登录
        if (tbUser == null) {
            return true;
        }
        //已登录
        else {
            httpServletResponse.sendRedirect("/index");
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
