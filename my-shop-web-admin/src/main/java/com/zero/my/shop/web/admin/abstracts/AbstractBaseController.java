package com.zero.my.shop.web.admin.abstracts;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.commons.dto.PageInfo;
import com.zero.my.shop.commons.persistence.BaseEntity;
import com.zero.my.shop.commons.persistence.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @classname: AbstractBaseController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-28 15:53
 **/
public abstract class AbstractBaseController<T extends BaseEntity,S extends BaseService<T>> {

    @Autowired
    protected S service;

    /**
     * 跳转列表页
     * @return
     */
    public abstract String list();

    /**
     * 跳转表单页
     * @return
     */
    public abstract String form();

    public abstract String save(T entity, Model model, RedirectAttributes redirectAttributes);

    public abstract BaseResult delete(String ids);

    public abstract String detail(T entity);

    @ResponseBody
    @RequestMapping(value = "page",method = RequestMethod.GET)
    public PageInfo<T> page(HttpServletRequest request, T entity) {
        Map<String,Object> result = new HashMap<>();

        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLenth = request.getParameter("length");

        int draw = strDraw == null ? 0:Integer.parseInt(strDraw);
        int start = strStart == null ? 0:Integer.parseInt(strStart);
        int length = strLenth == null ? 10:Integer.parseInt(strLenth);

        //封装dataTables需要的结果
        PageInfo<T> pageInfo = service.page(start,length,draw,entity);

        /*
        //处理上传的文件名 和在 UpLoadController中处理效果相同
        List<TbContent> tbContents = pageInfo.getData();
        for (TbContent content : tbContents) {
            content.setPic("/static/upload/" + content.getPic());
        }
        */
        return pageInfo;
    }
}
