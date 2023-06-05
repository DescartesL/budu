package com.budu.entity.crawler;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.Date;

/**
 * @author DesLUO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("b_crawler")
@ApiModel(value="crawler对象", description="知鸦爬虫表")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZhiYaCrawler {
    private Long id;
    private String title;
    private String url;
    private String summary;
    private String time;
    private Date createTime;
}
