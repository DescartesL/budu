package com.eula.budu.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 日志表
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_log")
@ApiModel(value="UserLog对象", description="日志表")
public class UserLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "操作用户ID")
    private Long userId;

    @ApiModelProperty(value = "ip地址")
    private String ip;

    @ApiModelProperty(value = "操作地址")
    private String address;

    @ApiModelProperty(value = "操作类型")
    private String type;

    @ApiModelProperty(value = "操作日志")
    private String description;

    @ApiModelProperty(value = "操作模块")
    private String model;

    @ApiModelProperty(value = "操作时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "操作结果")
    private String result;

    @ApiModelProperty(value = "操作系统")
    private String accessOs;

    @ApiModelProperty(value = "浏览器")
    private String browser;

    @ApiModelProperty(value = "客户端类型")
    private String clientType;


}
