package com.budu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.budu.entity.crawler.ZhiYaCrawler;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ZhiYaCrawlerMapper extends BaseMapper<ZhiYaCrawler> {
}
