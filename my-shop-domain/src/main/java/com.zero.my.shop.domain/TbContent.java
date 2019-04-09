package com.zero.my.shop.domain;

import com.zero.my.shop.commons.persistence.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @classname: TbContent
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-25 17:18
 **/
@Data
public class TbContent extends BaseEntity {

//    private Long categoryId;

    @Length(min = 1,max = 20,message = "标题的长度必须介于1 - 20个字符之间")
    private String title;

    @Length(min = 1,max = 20,message = "子标题的长度必须介于1 - 20个字符之间")
    private String subTitle;

    @Length(min = 1,max = 50,message = "子标题的长度必须介于1 - 50个字符之间")
    private String titleDesc;

    private String url;
    private String pic;
    private String pic2;

    @NotNull(message = "内容不能为空")
    private String content;
    @NotNull(message = "父级类目不能为空")
    private TbContentCategory tbContentCategory;

}
