package com.zero.my.shop.web.admin.dao;

import com.zero.my.shop.commons.persistence.BaseDao;
import com.zero.my.shop.domain.TbUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TbUserDao extends BaseDao<TbUser> {
    /**
     * 使用用户名模糊查询
     * @param username
     * @return
     */
    List<TbUser> selectByName(String username);

    /**
     * 使用email查询用户
     * @param email
     * @return
     */
    TbUser getByEmail(String email);

    /**
     * 搜索
     * @param tbUser
     * @return
     */
    List<TbUser> search(TbUser tbUser);

}
