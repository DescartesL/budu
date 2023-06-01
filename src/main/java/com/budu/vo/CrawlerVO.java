package com.budu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrawlerVO {
    private String name;
    private String url;
    private String cron;
    private String type;
    private String status;
    private String description;

}
