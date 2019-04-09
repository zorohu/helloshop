package com.zero.my.shop.web.api.service;

import com.zero.my.shop.domain.TbUser;

/**
 * 会员管理
 * @classname: TbUserService
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-31 09:39
 **/
public interface TbUserService {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
