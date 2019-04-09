package com.zero.my.shop.web.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @classname: RegisterController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-30 20:06
 **/
@Controller
public class RegisterController {

    /**
     * 跳转到登陆页
     * @return
     */
    @RequestMapping(value = "register",method = RequestMethod.GET)
    public String index() {
        return "register";
    }
}
