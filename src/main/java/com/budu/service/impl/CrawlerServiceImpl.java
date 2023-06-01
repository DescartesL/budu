package com.budu.service.impl;

import com.budu.common.ResponseResult;
import com.budu.entity.crawler.Crawler;
import com.budu.entity.crawler.ZhiYaCrawler;
import com.budu.mapper.crawler.CrawlerMapper;
import com.budu.mapper.crawler.ZhiYaCrawlerMapper;
import com.budu.service.CrawlerService;
import com.budu.vo.CrawlerVO;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CrawlerServiceImpl implements CrawlerService {

    private final CrawlerMapper crawlerMapper;

    private final ZhiYaCrawlerMapper zhiYaMapper;


    @Override
    public ResponseResult getZhiYa() throws IOException {
        Document doc = Jsoup.connect("https://tool.lu/article/report/").get();
        Elements divs =  doc.select(".page-inner");
        Elements time = divs.select("> p");
        Elements children = divs.select("> div");
        List<ZhiYaCrawler> crawlers = new ArrayList<>();
        children.forEach(
                x -> {
                    ZhiYaCrawler crawler = new ZhiYaCrawler();
                    crawler.setTitle(x.select(".ct-heading").text());
                    crawler.setUrl(x.select(".ct-link").attr("href"));
                    crawler.setSummary(x.select(".ct-note-content p").text());
                    crawler.setTime(time.get(0).text());
                    crawler.setCreateTime(new Date());
                    zhiYaMapper.insert(crawler);
                }
        );
        return ResponseResult.success("爬取成功！");
    }

    @Override
    public ResponseResult addCrawler(CrawlerVO vo) {
        Crawler crawler = Crawler.builder().name(vo.getName()).
                                url(vo.getUrl()).
                                cron(vo.getCron()).
                                type(vo.getType()).
                                status(vo.getStatus()).
                                description(vo.getDescription()).
                                build();
        crawlerMapper.insert(crawler);
        return ResponseResult.success("添加成功！");
    }

    @Override
    public ResponseResult deleteCrawlerById(Integer id) {
        return null;
    }

    @Override
    public ResponseResult listCrawler() {
        return null;
    }

    @Override
    public ResponseResult updateCrawler(Crawler crawler) {
        return null;
    }
}
