package com.budu.service;

import com.budu.common.ResponseResult;
import com.budu.entity.PhotoAlbum;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 相册 服务类
 * </p>
 *
 * @author blue
 * @since 2021-12-29
 */
public interface PhotoAlbumService extends IService<PhotoAlbum> {

    /**
     * 相册列表
     * @param name 相册名称
     * @return {@link ResponseResult}
     */
    ResponseResult listAlbum(String name);

    /**
     * 相册详情
     * @param id  相册id
     * @return {@link ResponseResult}
     */
    ResponseResult getAlbumById(Integer id);

    /** 添加相册
     * @param photoAlbum 相册信息
     * @return {@link ResponseResult}
     */
    ResponseResult insertAlbum(PhotoAlbum photoAlbum);

    /**
     * 修改相册
     * @param photoAlbum 相册信息
     * @return {@link ResponseResult}
     */
    ResponseResult updateAlbum(PhotoAlbum photoAlbum);

    /**
     * 删除相册
     * @param id 相册id
     * @return {@link ResponseResult}
     */
    ResponseResult deleteAlbumById(Integer id);


    // web端方法开始 给用户看的
    /**
     * web端相册列表
     * @return {@link ResponseResult}
     */
    ResponseResult webAlbumList();

    /**
     * web端相册详情
     * @param albumId 相册id
     * @return {@link ResponseResult}
     */
    ResponseResult webListPhotos(Integer albumId);

}
