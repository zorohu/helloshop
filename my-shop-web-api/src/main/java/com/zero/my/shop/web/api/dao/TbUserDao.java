package com.zero.my.shop.web.api.dao;

import com.zero.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

/**
 * 会员管理
 */
@Repository
public interface TbUserDao {

    /**
     * 登录
     * @param tbUser
     * @return
     */
    TbUser login(TbUser tbUser);
}
