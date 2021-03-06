package com.zero.my.shop.web.ui.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;

/**
 * @classname: TbUser
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-31 10:40
 **/
@Data
public class TbUserDto implements Serializable {
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String email;
    private String verification;
}
