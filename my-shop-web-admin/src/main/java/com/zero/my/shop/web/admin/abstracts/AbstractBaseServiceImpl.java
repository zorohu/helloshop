package com.zero.my.shop.web.admin.abstracts;

import com.zero.my.shop.commons.dto.PageInfo;
import com.zero.my.shop.commons.persistence.BaseDao;
import com.zero.my.shop.commons.persistence.BaseEntity;
import com.zero.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @classname: AbstractBaseServiceImpl
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-28 14:10
 **/
public abstract class AbstractBaseServiceImpl<T extends BaseEntity,D extends BaseDao<T>> implements BaseService<T> {

    @Autowired
    protected D dao;

    /**
     * 查询所有信息
     * @return
     */
    public List<T> selectAll(){
        return dao.selectAll();
    }

    /**
     * 删除用户
     * @param id
     */
    public void delete(Long id) {
        dao.delete(id);
    }

    /**
     * 根据ＩＤ查找用户
     * @param id
     * @return
     */
    public T getById(Long id) {
        return dao.getById(id);
    }

    /**
     * 更新用户信息
     * @param entity
     */
    public void update(T entity) {
        dao.update(entity);
    }

    /**
     * 删除选中用户
     * @param ids
     */
    public void deleteMulti(String[] ids){
        dao.deleteMulti(ids);
    }

    /**　
     * 查询用户数
     * @param Entity
     * @return
     */
    public int count(T Entity) {
        return dao.count(Entity);
    }

//    public List<T> page(Map<String,Object> params) {
//        return dao.page(params);
//    }

    @Override
    public PageInfo<T> page(int start, int length, int draw, T entity) {
        int count = count(entity);

        Map<String,Object> params = new HashMap<>();
        params.put("start",start);
        params.put("length",length);
        params.put("pageParams",entity);

        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);
        pageInfo.setRecordsTotal(count);
        pageInfo.setRecordsFiltered(count);
        pageInfo.setData(dao.page(params));

        return pageInfo;
    }
}
