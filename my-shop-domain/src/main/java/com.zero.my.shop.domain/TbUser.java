package com.zero.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zero.my.shop.commons.persistence.BaseEntity;
import com.zero.my.shop.commons.utils.RegexpUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;


/**
 * @classname: TbUser
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-18 20:17
 **/
@Data
public class TbUser extends BaseEntity {
    @Length(min = 6,max =12,message = "用户名的长度应为6 - 12个字符")
    private String username;

    @JsonIgnore
    @Length(min = 6,max =20,message = "密码长度必须介于6 - 20位之间")
    private String password;

    @Pattern(regexp = RegexpUtils.PHONE,message = "请输入正确的手机号")
    private String phone;

    @Pattern(regexp = RegexpUtils.EMAIL,message = "请输入正确的邮箱")
    private String email;

}
