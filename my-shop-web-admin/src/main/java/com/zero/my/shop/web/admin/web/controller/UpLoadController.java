package com.zero.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @classname: UpLoadController
 * @description: TODO
 * @author: Author.zero
 * @create: 2019-03-26 18:47
 **/
@Controller
public class UpLoadController {

    private static final String UPLOAD_PATH = "/static/upload/";

    /**
     *
     * @param dropFile dropFile是dropzone的上传的文件名
     * @param editorFiles editorFile是wangEditor上传的文件名
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public Map<String,Object> upload(MultipartFile dropFile,MultipartFile[] editorFiles, HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>();

        if (dropFile != null) {
            result.put("fileName",writeFile(dropFile,request));
        }

        if (editorFiles != null && editorFiles.length>0) {
            List<String> fileNames = new ArrayList<>();

            //循环写文件  并将文件名存进list
            for (MultipartFile editorFile : editorFiles) {
                fileNames.add(writeFile(editorFile,request));
            }

            result.put("errno",0);
            result.put("data",fileNames);
        }

//        //文件名
//        String fileName = myFile.getOriginalFilename();
//        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
//        //文件存放路径
//        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
//        //判断文件路径是否存在 不存在则创建
//        File file = new File(filePath);
//        if (!file.exists()) {
//            file.mkdir();
//        }
//
//        //将文件写入目标目录
//        file = new File(filePath, UUID.randomUUID()+fileSuffix);
//        try {
//            myFile.transferTo(file);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        if (dropFile != null) {
//            result.put("fileName",UPLOAD_PATH+file.getName());
//        }
//        else {
//            /**　
//             * scheme :服务端提供的协议
//             * ServerName :服务器名称 localhost/ip/domain
//             * ServerPort :服务器端口
//             */
//            // 获取服务端路径
//            String serverPath =
//                    String.format("%s://%s:%s%s%s",
//                            request.getScheme(),
//                            request.getServerName(),
//                            request.getServerPort(),
//                            request.getContextPath(),
//                            UPLOAD_PATH);
//
//        }

        return result;
    }

    private String writeFile(MultipartFile multipartFile,HttpServletRequest request) {
        //获取文件后缀
        //文件名
        String fileName = multipartFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));

        //文件存放路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);

        //判断文件路径是否存在 不存在则创建
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdir();
        }

        //将文件写入目标目录
        file = new File(filePath, UUID.randomUUID()+fileSuffix);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取服务端路径
        String serverPath =
                String.format("%s://%s:%s%s%s",
                        request.getScheme(),
                        request.getServerName(),
                        request.getServerPort(),
                        request.getContextPath(),
                        UPLOAD_PATH);

        return serverPath+file.getName();
    }
}
