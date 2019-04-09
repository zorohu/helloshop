package com.zero.my.shop.web.admin.service.impl;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.commons.dto.PageInfo;
import com.zero.my.shop.commons.validator.BeanValidator;
import com.zero.my.shop.domain.TbContent;
import com.zero.my.shop.web.admin.abstracts.AbstractBaseServiceImpl;
import com.zero.my.shop.web.admin.dao.TbContentDao;
import com.zero.my.shop.web.admin.service.TbContentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @classname: TbContentServiceImpl
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-25 17:22
 **/
@Service
public class TbContentServiceImpl extends AbstractBaseServiceImpl<TbContent,TbContentDao> implements TbContentService{


    @Override
    public BaseResult save(TbContent entity) {
        String validator = BeanValidator.validator(entity);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //验证通过
        else {
            entity.setUpdated(new Date());
            //说明是新增用户
            if (null == entity.getId()) {
                //密码加密
                entity.setCreated(new Date());
                dao.insert(entity);
            }
            //编辑用户
            else {
                update(entity);
            }
            return BaseResult.success("保存内容信息成功!");
        }
    }

//    @Override
//    public PageInfo<TbContent> page(int start, int length, int draw, TbContent entity) {
//        int count = count(entity);
//
//        Map<String,Object> params = new HashMap<>();
//        params.put("start",start);
//        params.put("length",length);
//        params.put("pageParams",entity);
//
//        PageInfo<TbContent> pageInfo = new PageInfo<>();
//        pageInfo.setDraw(draw);
//        pageInfo.setRecordsTotal(count);
//        pageInfo.setRecordsFiltered(count);
//        pageInfo.setData(page(params));
//
//        return pageInfo;
//    }


}
