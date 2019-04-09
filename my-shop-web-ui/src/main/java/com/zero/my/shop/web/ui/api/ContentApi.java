package com.zero.my.shop.web.ui.api;

import com.zero.my.shop.commons.utils.HttpClientUtils;
import com.zero.my.shop.commons.utils.MapperUtils;
import com.zero.my.shop.web.ui.dto.TbContentDto;

import java.util.List;

/**
 * @classname: ContentApi
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-30 17:51
 **/
public class ContentApi {
    /**
     * 请求幻灯片
     *
     * @return
     */
    public static List<TbContentDto> ppt() {
        List<TbContentDto> tbContentDtos = null;
        String result = HttpClientUtils.doGet(API.API_CONTENTS_PPT);
        try {
            tbContentDtos = MapperUtils.json2listByTree(result, "data", TbContentDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tbContentDtos;
    }
}
