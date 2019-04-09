package com.zero.my.shop.web.api.web.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 内容数据传输对象
 * @classname: TbContentDto
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-29 18:39
 **/
@Data
public class TbContentDto implements Serializable {
    private Long id;
    private String title;
    private String subTitle;
    private String titleDesc;
    private String url;
    private String pic;
    private String pic2;

}
