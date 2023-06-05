package com.budu.service;


import com.budu.common.ResponseResult;
import com.budu.vo.CrawlerVO;

import java.io.IOException;

public interface CrawlerService {
    ResponseResult getZhiYa() throws IOException;

    ResponseResult addCrawler(CrawlerVO crawler);

    ResponseResult deleteCrawlerById(Integer id);

    ResponseResult listCrawler();

    ResponseResult updateCrawler(CrawlerVO vo);
}
