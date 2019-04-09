package com.zero.my.shop.web.admin.web.controller;

import com.zero.my.shop.commons.constant.ConstantUtils;
import com.zero.my.shop.domain.TbUser;
import com.zero.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @classname: LoginController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-17 18:58
 **/
@Controller
public class LoginController {
    @Autowired
    private TbUserService tbUserService;

    /**
     * 跳转登陆页面
     * @return
     */
    @RequestMapping(value = {"login",""}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 登陆逻辑
     * @param email
     * @param password
     * @return
     */
    @RequestMapping( value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String email,
                        @RequestParam(required = true) String password,
                        HttpServletRequest request,
                        Model model) {

        TbUser tbUser = tbUserService.login(email, password);
        if (null == tbUser) {
            model.addAttribute("message","用户名或密码错误请重新输入!");
            return login();
        }
        //登陆成功
        else {
            //将登陆信息放入回话
            request.getSession().setAttribute(ConstantUtils.SESSION_USER,tbUser);
            return "redirect:/main";
        }
    }

    /**
     * 注销
     * @param request
     * @return
     */
    @RequestMapping( value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return login();
    }
}
