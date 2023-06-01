package com.budu.entity.crawler;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.budu.util.DateUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@ApiModel(value="Crawler对象", description="")
@TableName("b_crawlers")
@AllArgsConstructor
@NoArgsConstructor
public class Crawler implements Serializable {
    @ApiModelProperty(value = "爬虫id")
    private String id;

    @ApiModelProperty(value = "爬虫名称")
    private String name;

    @ApiModelProperty(value = "爬虫url")
    private String url;

    @ApiModelProperty(value = "定时信息")
    private String cron;

    @ApiModelProperty(value = "爬虫类型")
    private String type;

    @ApiModelProperty(value = "爬虫状态")
    private String status;

    @ApiModelProperty(value = "爬虫描述")
    private String description;

    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = DateUtils.FORMAT_STRING,timezone="GMT+8")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}
