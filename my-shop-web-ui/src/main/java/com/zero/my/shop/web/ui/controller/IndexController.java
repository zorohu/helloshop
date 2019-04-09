package com.zero.my.shop.web.ui.controller;

import com.zero.my.shop.commons.utils.HttpClientUtils;
import com.zero.my.shop.commons.utils.MapperUtils;
import com.zero.my.shop.web.ui.api.ContentApi;
import com.zero.my.shop.web.ui.dto.TbContentDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @classname: IndexController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-30 12:13
 **/
@Controller
public class IndexController {

    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = {"","index"},method = RequestMethod.GET)
    public String index(Model model) {
        requestContentsPPT(model);
        return "index";
    }

    /**
     * 请求幻灯片
     *
     * @param model
     */
    private void requestContentsPPT(Model model) {
        List<TbContentDto> tbContents = ContentApi.ppt();
        model.addAttribute("ppt", tbContents);
    }
}
