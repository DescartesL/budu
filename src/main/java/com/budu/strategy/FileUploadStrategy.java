package com.budu.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传策略
 */
public interface FileUploadStrategy {

    /**
     * 文件上传
     * @param file 文件对象
     * @param suffix 文件后缀
     * @return {@link String} 文件名
     */
    String fileUpload(MultipartFile file,String suffix);

    /**
     * 删除文件
     * @param fileName 文件名
     * @return {@link Boolean}
     */
    Boolean deleteFile(String ...fileName);
}
