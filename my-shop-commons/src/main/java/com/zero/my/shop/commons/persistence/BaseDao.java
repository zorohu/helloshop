package com.zero.my.shop.commons.persistence;

import java.util.List;
import java.util.Map;

/**
 * @classname: BaseDao
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-27 15:34
 **/
public interface BaseDao<T extends BaseEntity> {
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
     * 批量删除
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页查询
     * @param params 需要两个参数，start记录开始的位置，length每页的数量
     * @return
     */
    List<T> page(Map<String,Object> params);

    /**
     * 查询总笔数
     * @return
     */
    int count(T Entity);
}
