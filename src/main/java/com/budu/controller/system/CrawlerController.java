package com.budu.controller.system;

import com.budu.common.ResponseResult;
import com.budu.entity.crawler.Crawler;
import com.budu.service.CrawlerService;
import com.budu.vo.CrawlerVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DesLUO
 */
@RestController
@RequestMapping("/monitor/crawler")
@AllArgsConstructor
public class CrawlerController {

    private final CrawlerService crawlerService;

    @PostMapping("/add")
    public ResponseResult addCrawler(@RequestBody CrawlerVO vo) {
        return crawlerService.addCrawler(vo);
    }

    @PostMapping("/delete")
    public ResponseResult deleteCrawler(Integer id) {
        return crawlerService.deleteCrawlerById(id);
    }

    @PostMapping("/update")
    public ResponseResult updateCrawler(@RequestBody Crawler crawler) {
        return crawlerService.updateCrawler(crawler);
    }

    @PostMapping("/list")
    public ResponseResult listCrawler() {
        return crawlerService.listCrawler();
    }




}
