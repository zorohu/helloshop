package com.zero.my.shop.web.admin.web.controller;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.domain.TbContent;
import com.zero.my.shop.web.admin.abstracts.AbstractBaseController;
import com.zero.my.shop.web.admin.service.TbContentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



/**
 * @classname: ContentController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-25 18:10
 **/
@Controller
@RequestMapping(value = "content")
public class ContentController extends AbstractBaseController<TbContent,TbContentService> {

    @Autowired
    private TbContentService tbContentService;

    //1.有这个注释的函数会在有@RequestMapping 方法之前执行(AOP)
    //2.如果返回的是一个对象那么会将这个对象放入到Model中去
    @ModelAttribute
    public TbContent getUser(Long id) {
        TbContent tbContent = null;

        if (id != null) {
            tbContent = tbContentService.getById(id);
        }
        else {
            tbContent = new TbContent();
        }
        return tbContent;
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list() {
        return "content_list";
    }

    /**
     * 跳转表单页
     * @return
     */
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form() {
        return "content_form";
    }

    /**
     *　保存信息
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbContent tbContent, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbContentService.save(tbContent);
        //保存成功
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/content/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return "content_form";
        }
    }

    /**
     * 删除信息
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            tbContentService.deleteMulti(idArray);
            baseResult = BaseResult.success("删除内容成功");
        }
        else {
            baseResult = BaseResult.fail("删除内容失败");
        }
        return baseResult;
    }

    /**
     * 显示用户详情
     * @param tbContent
     * @return
     */
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbContent tbContent) {
        return "content_detail";
    }
}
