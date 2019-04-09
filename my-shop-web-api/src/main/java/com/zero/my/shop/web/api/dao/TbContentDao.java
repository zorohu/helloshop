package com.zero.my.shop.web.api.dao;

import com.zero.my.shop.domain.TbContent;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @classname: TbContentDao
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-29 16:55
 **/
@Repository
public interface TbContentDao {
    /**
     * 根据类别ID 查询内容列表
     * @param tbContent
     * @return
     */
    List<TbContent> selectByCategoryId(TbContent tbContent);
}
