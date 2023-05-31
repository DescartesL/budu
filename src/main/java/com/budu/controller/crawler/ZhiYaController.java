package com.budu.controller.crawler;

import com.budu.common.ResponseResult;
import com.budu.service.CrawlerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Api(tags = "爬虫接口")
@RequestMapping("/crawler")
@RequiredArgsConstructor
public class ZhiYaController {

    private final CrawlerService crawlerService;

    @GetMapping("/zhiya")
    public ResponseResult crawlerZhiYa() throws IOException {
        return crawlerService.getZhiYa();
    }
}
