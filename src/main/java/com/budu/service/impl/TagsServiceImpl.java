package com.budu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.budu.vo.TagVO;
import com.budu.entity.Tags;
import com.budu.common.ResponseResult;
import com.budu.common.SqlConf;
import com.budu.mapper.TagsMapper;
import com.budu.service.TagsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.budu.util.PageUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import java.util.List;

import static com.budu.common.SqlConf.LIMIT_ONE;

/**
 * <p>
 * 博客标签表 服务实现类
 * </p>
 *
 * @author blue
 * @since 2021-09-09
 */
@Service
public class TagsServiceImpl extends ServiceImpl<TagsMapper, Tags> implements TagsService {

    /**
     * 标签列表
     * @param name
     * @return
     */
    @Override
    public ResponseResult listTags(String name) {
        Page<Tags> list = baseMapper.selectPageRecord(new Page<>(PageUtils.getPageNo(), PageUtils.getPageSize()),name);
        return ResponseResult.success(list);
    }

    /**
     * 标签详情
     * @param id
     * @return
     */
    @Override
    public ResponseResult getTagsById(Long id) {
        Tags tags = baseMapper.selectById(id);
        return ResponseResult.success(tags);
    }

    /**
     * 添加标签
     * @param tags
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult insertTag(Tags tags) {
        validateName(tags.getName());
        baseMapper.insert(tags);
        return ResponseResult.success();
    }

    /**
     * 修改标签
     * @param tags
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult updateTag(Tags tags) {
        Tags entity = baseMapper.selectById(tags.getId());
        if (!entity.getName().equals(tags.getName())) validateName(tags.getName());
        baseMapper.updateById(tags);
        return ResponseResult.success();
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult deleteById(Long id) {
        baseMapper.deleteById(id);
        return ResponseResult.success();
    }

    /**
     * 批量删除标签
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult deleteBatch(List<Long> ids) {
        baseMapper.deleteBatchIds(ids);
        return ResponseResult.success();
    }

    /**
     * 置顶标签
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseResult top(Long id) {
        Tags tags = baseMapper.selectOne(new QueryWrapper<Tags>()
                .last(LIMIT_ONE).orderByDesc(SqlConf.SORT));
        Assert.isTrue(!tags.getId().equals(id),"改标签已在最顶端!");
        Tags entity = Tags.builder().id(id).sort(tags.getSort()+1).build();
        int rows = baseMapper.updateById(entity);
        return rows > 0 ? ResponseResult.success(): ResponseResult.error();
    }


    //    ----web端方法开始-----
    /**
     *  标签列表
     * @return
     */
    @Override
    public ResponseResult webList() {
        List<TagVO> list = baseMapper.selectAll();
        return ResponseResult.success(list);
    }

    //-----------自定义方法开始------------
    public void validateName(String name){
        Tags entity = baseMapper.selectOne(new QueryWrapper<Tags>().eq(SqlConf.NAME,name));
        Assert.isNull(entity,"标签名已存在!");
    }
}