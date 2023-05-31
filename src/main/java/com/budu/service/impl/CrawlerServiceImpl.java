package com.budu.service.impl;

import com.budu.common.ResponseResult;
import com.budu.entity.ZhiYaCrawler;
import com.budu.mapper.ZhiYaMapper;
import com.budu.service.CrawlerService;
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

    private final ZhiYaMapper zhiYaMapper;

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
}
