package com.zero.my.shop.web.api.web.controller.v1;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.domain.TbUser;
import com.zero.my.shop.web.api.dao.TbUserDao;
import com.zero.my.shop.web.api.service.TbUserService;
import com.zero.my.shop.web.api.web.dto.TbUserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员管理
 * @classname: TbUserController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-31 09:53
 **/
@RestController
@RequestMapping(value = "${api.path.v1}/users")
public class TbUserController {
    @Autowired
    private TbUserService tbUserService;

    /**
     * 登录
     * @param tbUser
     * @return
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResult login(TbUser tbUser) {
        TbUser user = tbUserService.login(tbUser);
        if (user == null) {
            return BaseResult.fail("账号或密码错误！");
        }
        else  {
            TbUserDto dto = new TbUserDto();
            BeanUtils.copyProperties(user,dto);

            return BaseResult.success("成功",dto);
        }
    }
}
