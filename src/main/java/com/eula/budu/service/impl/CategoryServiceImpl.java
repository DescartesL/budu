package com.eula.budu.service.impl;

import com.eula.budu.entity.Category;
import com.eula.budu.mapper.CategoryMapper;
import com.eula.budu.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 博客分类表 服务实现类
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

}
