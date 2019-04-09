package com.zero.my.shop.web.ui.controller;

import com.google.code.kaptcha.Constants;
import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.commons.utils.EmailSendUntils;
import com.zero.my.shop.web.ui.api.UsersApi;
import com.zero.my.shop.web.ui.constant.SystemConstants;
import com.zero.my.shop.web.ui.dto.TbUserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @classname: LoginController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-30 19:57
 **/
@Controller
public class LoginController {

    @Autowired
    private EmailSendUntils emailSendUntils;
    /**
     * 跳转到登陆页
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 登录
     * @param tbUserDto
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(TbUserDto tbUserDto, Model model,  HttpServletRequest request) throws Exception {
        //验证码验证失败
        if (!checkVerification(tbUserDto,request)) {
            model.addAttribute("baseResult",BaseResult.fail("验证码输入错误，请重新输入"));
            return "login";
        }

        TbUserDto user = UsersApi.login(tbUserDto);
//        System.out.println(user.toString());
        //登录失败
        if (user == null) {
            model.addAttribute("baseResult",BaseResult.fail("用户名或密码错误!"));
            return "login";
        }
        //登录成功
        else {
            emailSendUntils.send("用户登录",String.format("用户 【%s】 登录MyShop",user.getUsername()),"15172385509@163.com");
            //将用户信息放入session
            request.getSession().setAttribute(SystemConstants.SESSION_USER_KEY,user);
            //重定向到首页
            return "redirect:/index";
        }
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index";
    }

    private boolean checkVerification (TbUserDto userDto,HttpServletRequest request){
        String verification = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if (StringUtils.equals(verification,userDto.getVerification())) {
            return true;
        }
        return false;
    }
}
