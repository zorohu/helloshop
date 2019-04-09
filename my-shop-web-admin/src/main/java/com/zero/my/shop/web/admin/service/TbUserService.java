package com.zero.my.shop.web.admin.service;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.commons.dto.PageInfo;
import com.zero.my.shop.commons.persistence.BaseService;
import com.zero.my.shop.domain.TbUser;

import java.util.List;

public interface TbUserService extends BaseService<TbUser> {
    /**
     * 登录
     * @param email
     * @param password
     * @return
     */
    TbUser login(String email,String password);

}
