package com.zero.my.shop.web.admin.abstracts;

import com.zero.my.shop.commons.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @classname: AbstractBaseTreeServiceImpl
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-28 10:37
 **/
public abstract class AbstractBaseTreeServiceImpl<T extends BaseEntity,D extends BaseTreeDao<T>> implements BaseTreeService<T> {

    @Autowired
    protected D dao;


    @Override
    @Transactional(readOnly = false)
    public void update(T Entity) {
        dao.update(Entity);
    }
    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(Long id) {
        dao.delete(id);
    }


    /**
     * 查询全部数据
     *
     * @return
     */
    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }


    /**
     * 根据 ID 查询信息
     *
     * @param id
     * @return
     */
    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }


    /**
     * 根据父级节点 ID 查询所有子节点
     *
     * @param pid
     * @return
     */
    @Override
    public List<T> selectByPid(Long pid) {
        return dao.selectByPid(pid);
    }

}
