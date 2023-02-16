package com.eula.budu.service.impl;

import com.eula.budu.entity.Photo;
import com.eula.budu.mapper.PhotoMapper;
import com.eula.budu.service.IPhotoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 照片 服务实现类
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements IPhotoService {

}
