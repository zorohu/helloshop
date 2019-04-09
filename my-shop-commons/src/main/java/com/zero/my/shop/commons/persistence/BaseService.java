package com.zero.my.shop.commons.persistence;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.commons.dto.PageInfo;

import java.util.List;

public interface BaseService<T extends BaseEntity>{
    /**
     * 查询所有用户
     * @return
     */
    List<T> selectAll();

    /**
     * 保存用户
     * @param Entity
     * @return
     */
    BaseResult save(T Entity);

    /**
     * 删除用户
     * @param id
     */
    void delete(Long id);

    /**
     * 根据ＩＤ查找用户
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 更新用户信息
     * @param tbUser
     */
    void update(T tbUser);

    /**
     * 删除选中用户
     * @param ids
     */
    void deleteMulti(String[] ids);

    /**
     * 分页
     * @param start
     * @param length
     * @param draw
     * @param Entity
     * @return
     */
    PageInfo<T> page(int start, int length, int draw, T Entity);

    /**　
     * 查询用户数
     * @param Entity
     * @return
     */
    int count(T Entity);
}
