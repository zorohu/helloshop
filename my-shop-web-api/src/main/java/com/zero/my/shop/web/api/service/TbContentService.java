package com.zero.my.shop.web.api.service;

import com.zero.my.shop.domain.TbContent;

import java.util.List;

public interface TbContentService {
    /**
     * 根据类别ID 查询内容列表
     * @param tbContent
     * @return
     */
    List<TbContent> selectByCategoryId(Long categoryId);
}
