package com.zero.my.shop.web.admin.service.impl;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.commons.dto.PageInfo;
import com.zero.my.shop.commons.validator.BeanValidator;
import com.zero.my.shop.domain.TbUser;
import com.zero.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.zero.my.shop.web.admin.dao.TbUserDao;
import com.zero.my.shop.web.admin.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @classname: TbUserServiceImpl
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-18 20:28
 **/
@Service
public class TbUserServiceImpl extends AbstractBaseServiceImpl<TbUser,TbUserDao> implements TbUserService{


    @Override
    public TbUser login(String email, String password) {
        TbUser user = dao.getByEmail(email);
        if (null != user) {
            //明文密码加密
            String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
            //判断密码是否正确
            if ( md5Password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public BaseResult save(TbUser entity) {
        String validator = BeanValidator.validator(entity);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //通过验证
        else {
            entity.setUpdated(new Date());
            entity.setPassword(DigestUtils.md5DigestAsHex(entity.getPassword().getBytes()));
            //说明是新增用户
            if (null == entity.getId()) {
                //密码加密
                entity.setPassword(DigestUtils.md5DigestAsHex(entity.getPassword().getBytes()));
                entity.setCreated(new Date());
                dao.insert(entity);
            }
            //编辑用户
            else {
                update(entity);
            }
            return BaseResult.success("保存用户信息成功!");
        }
    }
}
