package com.budu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.budu.entity.crawler.Crawler;
import com.budu.entity.crawler.ZhiYaCrawler;
import org.springframework.stereotype.Repository;


@Repository
public interface CrawlerMapper extends BaseMapper<Crawler> {
}
