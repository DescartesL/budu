package com.budu.quartz.job;

import com.budu.entity.crawler.ZhiYaCrawler;
import com.budu.mapper.ZhiYaCrawlerMapper;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * @author DesLUO
 */
@Component

public class ZhiYaCrawlerJob implements Job {

    @Autowired
    private ZhiYaCrawlerMapper mapper;

    public ZhiYaCrawlerJob() {
    }
    private final Logger LOGGER = LoggerFactory.getLogger(ZhiYaCrawlerJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            getZhiYa();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("ZhiYaCrawlerJob.execute", "执行成功");

    }

    public void getZhiYa() throws IOException {
        Document doc = Jsoup.connect("https://tool.lu/article/report/").get();
//        Document doc = Jsoup.connect("https://tool.lu/article/report/2023-06-03/").get();
        Elements divs =  doc.select(".page-inner");
        Elements time = divs.select("> p");
        Elements children = divs.select("> div");
        children.forEach(
                x -> {
                    ZhiYaCrawler crawler = new ZhiYaCrawler();
                    crawler.setTitle(x.select(".ct-heading").text());
                    crawler.setUrl(x.select(".ct-link").attr("href"));
                    crawler.setSummary(x.select(".ct-note-content p").text());
                    crawler.setTime(time.get(0).text());
                    crawler.setCreateTime(new Date());
                    mapper.insert(crawler);
                }
        );
    }
}
