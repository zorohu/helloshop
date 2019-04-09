package com.zero.my.shop.web.admin.abstracts;

import com.zero.my.shop.commons.persistence.BaseTreeEntity;
import com.zero.my.shop.commons.persistence.BaseTreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * @classname: AbstractBaseTreeController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-28 16:35
 **/
public abstract class AbstractBaseTreeController<T extends BaseTreeEntity,S extends BaseTreeService<T>> {

    @Autowired
    protected S service;
    /**
     * 跳转列表页
     * @param model
     * @return
     */
    public abstract String list(Model model);

    /**　
     * 跳转到form表单页
     * @param entity
     * @return
     */
    public abstract String form(T entity);

    /**
     * 保存
     * @param entity
     * @param model
     * @param redirectAttributes
     * @return
     */
    public abstract String save(T entity,Model model, RedirectAttributes redirectAttributes);

    /**
     * 树形结构
     * @param id
     * @return
     */
    public abstract List<T> treeData(Long id);


    /**
     *
     * @param sourceList 数据源集合
     * @param targetList 排序后集合
     * @param parentID 父节点的ID
     */
    protected void sortList(List<T> sourceList,List<T> targetList,Long parentID) {
        for (T sourceEntity:sourceList) {
            if (sourceEntity.getParent().getId().equals(parentID)){
                targetList.add(sourceEntity);

                //判断是不是父节点,如果有则继续追加使用递归
                if (sourceEntity.getIsParent()) {
                    for (T currentEntity: sourceList) {
                        //判断这个节点的父节点是不是上面的循环外的节点
                        if (currentEntity.getParent().getId().equals(sourceEntity.getId())) {
                            sortList(sourceList,targetList,sourceEntity.getId());
                            break;
                        }
                    }
                }
            }
        }
    }
}
