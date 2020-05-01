package com.zsh.restaurantsystem.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUtils {
    public static String upload(MultipartFile file, String path, String fileName){

        // 生成新的文件名
        String realName = FikeNameUtils.getFileName(fileName);
        String realPath = path + "/" + realName;

        //使用原文件名
        // String realPath = path + "/" + fileName;

        File dest = new File(realPath);

        //判断文件父目录是否存在
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdir();
        }

        try {
            //保存文件
            file.transferTo(dest);
            return realName;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return "0";
        } catch (IOException e) {
            e.printStackTrace();
            return "0";
        }
    }

}
