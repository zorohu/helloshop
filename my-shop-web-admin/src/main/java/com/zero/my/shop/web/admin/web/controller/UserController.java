package com.zero.my.shop.web.admin.web.controller;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.domain.TbUser;
import com.zero.my.shop.web.admin.abstracts.AbstractBaseController;
import com.zero.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @classname: UserController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-19 20:59
 **/
@Controller
@RequestMapping(value = "user")
public class UserController extends AbstractBaseController<TbUser,TbUserService> {

    //1.有这个注释的函数会在有@RequestMapping 方法之前执行(AOP)
    //2.如果返回的是一个对象那么会将这个对象放入到Model中去
    @ModelAttribute
    public TbUser getUser(Long id) {
        TbUser tbUser = null;

        if (id != null) {
            tbUser = service.getById(id);
        }
        else {
            tbUser = new TbUser();
        }
        return tbUser;
    }

    /**
     * 跳转用户表单页
     * @return
     */
    @Override
    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list() {
        return "user_list";
    }

    /**
     * 跳转用户表单页
     * @return
     */
    @Override
    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form() {
        return "user_form";
    }

    /**
     *　保存用户
     * @param tbUser
     * @return
     */
    @Override
    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(TbUser tbUser,Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = service.save(tbUser);
        //保存成功
        if (baseResult.getStatus() == 200) {
            redirectAttributes.addFlashAttribute("baseResult",baseResult);
            return "redirect:/user/list";
        }
        //保存失败
        else {
            model.addAttribute("baseResult",baseResult);
            return "user_form";
        }
    }

    /**
     * 删除用户
     * @param ids
     * @return
     */
    @Override
    @ResponseBody
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public BaseResult delete(String ids) {
        BaseResult baseResult = null;
        if (StringUtils.isNotBlank(ids)) {
            String[] idArray = ids.split(",");
            service.deleteMulti(idArray);
            baseResult = BaseResult.success("删除用户成功");
        }
        else {
            baseResult = BaseResult.fail("删除用户失败");
        }
        return baseResult;
    }

//    /**
//     * 分页
//     * @param request
//     * @param tbUser
//     * @return
//     */
//    @Override
//    @ResponseBody
//    @RequestMapping(value = "page",method = RequestMethod.GET)
//    public PageInfo<TbUser> page(HttpServletRequest request,TbUser tbUser) {
//        Map<String,Object> result = new HashMap<>();
//
//        String strDraw = request.getParameter("draw");
//        String strStart = request.getParameter("start");
//        String strLenth = request.getParameter("length");
//
//        int draw = strDraw == null ? 0:Integer.parseInt(strDraw);
//        int start = strStart == null ? 0:Integer.parseInt(strStart);
//        int length = strLenth == null ? 10:Integer.parseInt(strLenth);
//
//        //封装dataTables需要的结果
//        PageInfo<TbUser> pageInfo = service.page(start,length,draw,tbUser);
//
//        return pageInfo;
//    }

    /**
     * 显示用户详情
     * @param tbUser
     * @return
     */
    @Override
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    public String detail(TbUser tbUser) {
        return "user_detail";
    }


}
