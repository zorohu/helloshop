package com.zero.my.shop.web.api.web.controller.v1;

import com.zero.my.shop.commons.dto.BaseResult;
import com.zero.my.shop.domain.TbContent;
import com.zero.my.shop.web.api.service.TbContentService;
import com.zero.my.shop.web.api.web.dto.TbContentDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @classname: TbContentController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-29 17:20
 **/
//@RestController这个注解返回的都是JSON
@RestController
@RequestMapping(value = "${api.path.v1}/contents")
public class TbContentController {

    @Autowired
    private TbContentService tbContentService;

    @ModelAttribute
    public TbContent getTbContent(Long id){
        TbContent tbContent = null;
        if (null == id) {
            tbContent = new TbContent();
        }

        return tbContent;
    }

    /**
     * 查询ppt
     * @return
     */
    @RequestMapping(value = "ppt",method = RequestMethod.POST)
    public BaseResult findPPT() {
        List<TbContentDto> tbContentDtos = null;
        List<TbContent> tbContents = tbContentService.selectByCategoryId(89L);

        if (tbContents != null && tbContents.size()>0) {
            tbContentDtos = new ArrayList<>();
            for (TbContent tbContent : tbContents) {
                TbContentDto dto = new TbContentDto();
                BeanUtils.copyProperties(tbContent,dto);
                tbContentDtos.add(dto);
            }
        }

        return BaseResult.success("成功",tbContentDtos);
    }


//    /**
//     * 根据URL的内容ID查询内容列表
//     * @param categoryId
//     * @return
//     */
//    @RequestMapping(value = "{category_id}",method = RequestMethod.GET)
//    public BaseResult findContentByCategoryId(@PathVariable(value = "category_id") Long categoryId) {
//        List<TbContentDto> tbContentDtos = null;
//        List<TbContent> tbContents = tbContentService.selectByCategoryId(categoryId);
//
//        if (tbContents != null && tbContents.size()>0) {
//            tbContentDtos = new ArrayList<>();
//            for (TbContent tbContent : tbContents) {
//                TbContentDto dto = new TbContentDto();
//                BeanUtils.copyProperties(tbContent,dto);
//                tbContentDtos.add(dto);
//            }
//        }
//
//        return BaseResult.success("成功",tbContentDtos);
//    }
}
