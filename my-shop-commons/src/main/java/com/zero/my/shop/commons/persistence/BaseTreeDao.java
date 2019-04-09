package com.zero.my.shop.commons.persistence;

import java.util.List;

/**
 * 通用的树形结构接口
 */
public interface BaseTreeDao<T extends BaseEntity> {
    /**
     * 查询所有信息
     * @return
     */
    List<T> selectAll();

    /**
     * 新增
     * @param Entity
     */
    void insert(T Entity);

    /**
     * 删除用户
     * @param id
     */
    void delete(Long id);

    /**
     * 根据ID查询信息
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新用户信息
     * @param Entity
     */
    void update(T Entity);

    /**
     * 根据父节点ID 查询所有子节点
     * @param pid
     * @return
     */
    List<T> selectByPid(Long pid);
}
