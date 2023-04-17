package com.budu.service.impl;


import com.budu.common.ResponseResult;

import com.budu.enums.FileUploadModelEnum;
import com.budu.service.CloudOssService;
import com.budu.service.SystemConfigService;
import com.budu.strategy.context.FileUploadStrategyContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

/**
 * @author DesLUO
 */
@Service
@RequiredArgsConstructor
public class CloudOssServiceImpl implements CloudOssService {

    private final SystemConfigService systemConfigService;

    private final FileUploadStrategyContext fileUploadStrategyContext;

    /**
     * 文件上传策略
     */
    private String strategy;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @Override
    public ResponseResult upload(MultipartFile file) {
        if (file.getSize() > 1024 * 1024 * 10) {
            return ResponseResult.error("文件大小不能大于10M");
        }
        //获取文件后缀
        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            return ResponseResult.error("请选择jpg,jpeg,gif,png格式的图片");
        }
        getFileUploadWay();
        String key = fileUploadStrategyContext.executeFileUploadStrategy(strategy, file, suffix);
        return ResponseResult.success(key);
    }


    /**
     * 删除文件
     * @param key 文件名
     * @return {@link ResponseResult}
     */
    @Override
    public ResponseResult delBatchFile(String ...key) {
        getFileUploadWay();
        Boolean isSuccess = fileUploadStrategyContext.executeDeleteFileStrategy(strategy, key);
        if (!isSuccess) {
            return ResponseResult.error("删除文件失败");
        }
        return ResponseResult.success();
    }

    /**
     * 获取文件上传方式，从数据库中拿到上传方式
     */
    private void getFileUploadWay() {
        strategy = FileUploadModelEnum.getStrategy(systemConfigService.getCustomizeOne().getFileUploadWay());
    }
}
