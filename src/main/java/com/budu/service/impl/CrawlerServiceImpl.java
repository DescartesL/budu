package com.budu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.budu.common.ResponseResult;
import com.budu.entity.crawler.Crawler;
import com.budu.entity.crawler.ZhiYaCrawler;
import com.budu.mapper.CrawlerMapper;
import com.budu.mapper.ZhiYaCrawlerMapper;
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
//        Document doc = Jsoup.connect("https://tool.lu/article/report/2023-06-03/").get();
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
        crawlerMapper.deleteById(id);
        return ResponseResult.success("删除成功！");
    }

    @Override
    public ResponseResult listCrawler() {
        QueryWrapper<Crawler> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "url", "cron", "type", "status", "description", "create_time");
        List<Crawler> crawlers = crawlerMapper.selectList(wrapper);
        return ResponseResult.success(crawlers);
    }

    @Override
    public ResponseResult updateCrawler(CrawlerVO vo) {
        Crawler crawler = Crawler.builder().
                name(vo.getName()).
                url(vo.getUrl()).
                cron(vo.getCron()).
                type(vo.getType()).
                status(vo.getStatus()).
                description(vo.getDescription()).
                build();
        crawlerMapper.update(crawler, new QueryWrapper<Crawler>().eq("name", crawler.getName()));
        return ResponseResult.success("更新成功！");
    }
}
