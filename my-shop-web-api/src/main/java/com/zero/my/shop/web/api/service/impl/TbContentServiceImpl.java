package com.zero.my.shop.web.api.service.impl;

import com.zero.my.shop.domain.TbContent;
import com.zero.my.shop.domain.TbContentCategory;
import com.zero.my.shop.web.api.dao.TbContentDao;
import com.zero.my.shop.web.api.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @classname: TbContentServiceImpl
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-29 17:13
 **/
@Service
@Transactional(readOnly = true)
public class TbContentServiceImpl implements TbContentService {

    @Autowired
    private TbContentDao tbContentDao;

    @Override
    public List<TbContent> selectByCategoryId(Long categoryId) {
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setId(categoryId);

        TbContent tbContent = new TbContent();
        tbContent.setTbContentCategory(tbContentCategory);

        return tbContentDao.selectByCategoryId(tbContent);
    }
}
