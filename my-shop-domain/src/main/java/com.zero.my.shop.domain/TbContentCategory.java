package com.zero.my.shop.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zero.my.shop.commons.persistence.BaseTreeEntity;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @classname: TbContentCategory
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-25 14:39
 **/
@Data
public class TbContentCategory extends BaseTreeEntity {
//    private Long parentId;
    @Length(min = 1,max = 20,message = "分类名称必须介于1 - 20位之间")
    private String name;
    private Integer status;
    @NotNull(message = "排序不能为空")
    private Integer sortOrder;
    @JsonProperty(value = "isParent")
    private Boolean isParent;
    private TbContentCategory parent;

}
