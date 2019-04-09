package com.zero.my.shop.web.ui.api;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.commons.utils.HttpClientUtils;
import com.zero.my.shop.commons.utils.MapperUtils;
import com.zero.my.shop.web.ui.dto.TbUserDto;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员管理接口
 * @classname: UsersApi
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-31 10:39
 **/
public class UsersApi {

    /**
     * 登录
     * @param tbUserdto
     * @return
     */
    public static TbUserDto login(TbUserDto tbUserdto) throws Exception {
        List<BasicNameValuePair> pargms = new ArrayList<>();
        pargms.add(new BasicNameValuePair("username",tbUserdto.getUsername()));
        pargms.add(new BasicNameValuePair("password",tbUserdto.getPassword()));

        String json = HttpClientUtils.doPost(API.API_USERS_LOGIN,pargms.toArray(new BasicNameValuePair[pargms.size()]));
        TbUserDto user = MapperUtils.json2pojoByTree(json, "data", TbUserDto.class);

        return user;
    }
}
