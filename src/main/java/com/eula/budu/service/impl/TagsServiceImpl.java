package com.eula.budu.service.impl;

import com.eula.budu.entity.Tags;
import com.eula.budu.mapper.TagsMapper;
import com.eula.budu.service.ITagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客标签表 服务实现类
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements ITagsService {

}
