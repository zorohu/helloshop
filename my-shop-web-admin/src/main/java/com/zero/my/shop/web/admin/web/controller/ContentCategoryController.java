package com.zero.my.shop.web.admin.web.controller;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.commons.persistence.BaseEntity;
import com.zero.my.shop.domain.TbContentCategory;
import com.zero.my.shop.web.admin.abstracts.AbstractBaseTreeController;
import com.zero.my.shop.web.admin.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

/**
 * @classname: TbContentCategoryController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-25 14:47
 **/
@Controller
@RequestMapping(value = "content/category")
public class ContentCategoryController extends AbstractBaseTreeController<TbContentCategory,TbContentCategoryService> {


    @ModelAttribute
    public TbContentCategory getTbContentCategory(Long id) {
        TbContentCategory tbContentCategory = null;

        // id 不为空，则从数据库获取
        if (id != null) {
            tbContentCategory = service.getById(id);
        } else {
            tbContentCategory = new TbContentCategory();
        }

        return tbContentCategory;
    }

    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model) {
        List<TbContentCategory> targetList = new ArrayList<>();
        List<TbContentCategory> sourceList = service.selectAll();

        //排序
        sortList(sourceList,targetList,0L);

        model.addAttribute("tbContentCategories",targetList);
        return "content_category_list";
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(TbContentCategory tbContentCategory) {
        return "content_category_form";
    }

    /**
     * 保存
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContentCategory tbContentCategory,Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbContentCategory);
        //保存成功
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/category/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return form(tbContentCategory);
        }
    }
    /**　
     * 树形结构
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/tree/data", method = RequestMethod.POST)
    public List<TbContentCategory> treeData(Long id) {
        if (id == null) {
            id = 0L;
        }
        return service.selectByPid(id);
    }
}
