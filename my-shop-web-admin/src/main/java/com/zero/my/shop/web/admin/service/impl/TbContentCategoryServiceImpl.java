package com.zero.my.shop.web.admin.service.impl;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.commons.dto.PageInfo;
import com.zero.my.shop.commons.validator.BeanValidator;
import com.zero.my.shop.domain.TbContentCategory;
import com.zero.my.shop.web.admin.abstracts.AbstractBaseTreeServiceImpl;
import com.zero.my.shop.web.admin.dao.TbContentCategoryDao;
import com.zero.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

/**
 * @classname: TbContentCategoryServiceImpl
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-25 14:46
 **/
@Service
@Transactional(readOnly = true)
public class TbContentCategoryServiceImpl extends AbstractBaseTreeServiceImpl<TbContentCategory,TbContentCategoryDao> implements TbContentCategoryService{

    @Override
    @Transactional(readOnly = false)
    public BaseResult save(TbContentCategory entity) {
        String validator = BeanValidator.validator(entity);
        //验证不通过
        if (validator != null) {
            return BaseResult.fail(validator);
        }
        //验证通过
        else {
            TbContentCategory parent = entity.getParent();

            //说明是新增用户
            if (null == parent || null == parent.getId()) {
                //如果没有选择父级节点默认设置为根目录
                parent.setId(0L);

            }
            entity.setUpdated(new Date());
            //新增节点
            if (entity.getId() == null) {
                entity.setCreated(new Date());
                entity.setIsParent(false);
                //判断当前节点有木有父节点//需要有父类节点才有箭头
                if (parent.getId() != 0L){
                    TbContentCategory currentCategoryParent = getById(parent.getId());
                    if (currentCategoryParent != null) {
                        //为父节点设置isParent为true
                        currentCategoryParent.setIsParent(true);
                        update(currentCategoryParent);
                    }
                }
                else {
                    //根目录一定是父级目录
                    entity.setIsParent(true);
                }
                dao.insert(entity);
            }
            //修改
            else {
                update(entity);
            }
            return BaseResult.success("保存分类信息成功!");
        }
    }
}

