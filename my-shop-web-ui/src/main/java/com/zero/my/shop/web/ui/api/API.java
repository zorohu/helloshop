package com.zero.my.shop.web.ui.api;

/**
 * @classname: API
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-30 17:41
 **/
public class API {
    //API主机地址
    public static final String HOST = "http://localhost:8090/api/v1";

    // 内容查询接口 - 幻灯片
    public static final String API_CONTENTS_PPT = HOST + "/contents/ppt";

    // 会员管理接口 - 登录
    public static final String API_USERS_LOGIN = HOST + "/users/login";
}
