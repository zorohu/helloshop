package com.zero.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类
 * @classname: BaseEntity
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-24 14:31
 **/
@Data
public abstract class BaseEntity  implements Serializable {
    private Long id;
    private Date created;
    private Date updated;

}
