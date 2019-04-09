package com.zero.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @classname: MainController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-17 18:59
 **/
@Controller
public class MainController {
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String main() {
        return "main";
    }
}
