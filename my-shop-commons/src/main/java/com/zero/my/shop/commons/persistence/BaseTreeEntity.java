package com.zero.my.shop.commons.persistence;

import lombok.Data;

import java.io.Serializable;

/**
 * @classname: BaseTreeEntity
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-28 16:56
 **/
@Data
public abstract class BaseTreeEntity<T extends BaseEntity> extends BaseEntity implements Serializable {
    private T parent;
    private Boolean isParent;
}
