package com.budu.service;


import com.budu.common.ResponseResult;
import com.budu.entity.crawler.Crawler;
import com.budu.vo.CrawlerVO;

import java.io.IOException;

public interface CrawlerService {
    ResponseResult getZhiYa() throws IOException;

    ResponseResult addCrawler(CrawlerVO crawler);

    ResponseResult deleteCrawlerById(Integer id);

    ResponseResult listCrawler();

    ResponseResult updateCrawler(Crawler crawler);
}
