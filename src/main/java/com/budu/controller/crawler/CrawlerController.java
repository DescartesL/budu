package com.budu.controller.crawler;

import com.budu.common.ResponseResult;
import com.budu.entity.crawler.Crawler;
import com.budu.service.CrawlerService;
import com.budu.vo.CrawlerVO;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public ResponseResult updateCrawler(@RequestBody CrawlerVO vo) {
        return crawlerService.updateCrawler(vo);
    }

    @GetMapping("/list")
    public ResponseResult listCrawler() {
        return crawlerService.listCrawler();
    }




}
