package com.sunlands.uedservice.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author lvpenghui
 * @date 2017/12/4 19:20
 */
public class FileUtil {

    public static String uploadFile(MultipartFile file, String filePath) throws Exception {
        String suffix ;
        String fileName = UUID.randomUUID() + "";
        try {
            File targetFile = new File(filePath);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf('.'));
            fileName += suffix;
            File serverFile = new File(filePath + fileName);
            file.transferTo(serverFile);
        }catch (Exception e){
            e.printStackTrace();
            return "上传失败！";
        }
        return filePath+fileName;
    }
}
