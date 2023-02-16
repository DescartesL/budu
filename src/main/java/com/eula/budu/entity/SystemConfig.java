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
 * 系统配置表
 * </p>
 *
 * @author eula
 * @since 2023-02-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("system_config")
@ApiModel(value="SystemConfig对象", description="系统配置表")
public class SystemConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "七牛云公钥")
    private String qiNiuAccessKey;

    @ApiModelProperty(value = "七牛云私钥")
    private String qiNiuSecretKey;

    @ApiModelProperty(value = "七牛云存储区域 华东（z0），华北(z1)，华南(z2)，北美(na0)，东南亚(as0)")
    private String qiNiuArea;

    @ApiModelProperty(value = "七牛云上传空间")
    private String qiNiuBucket;

    @ApiModelProperty(value = "七牛云域名前缀：http://images.moguit.cn")
    private String qiNiuPictureBaseUrl;

    @ApiModelProperty(value = "文件上传七牛云(0:否， 1:是)")
    private String uploadQiNiu;

    @ApiModelProperty(value = "是否开启注册用户邮件激活(0:否， 1:是)")
    private String openEmailActivate;

    @ApiModelProperty(value = "是否开启邮件通知(0:否， 1:是)")
    private String startEmailNotification;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否开启仪表盘通知(0:否， 1:是)")
    private String openDashboardNotification;

    @ApiModelProperty(value = "仪表盘通知【用于首次登录弹框】MD")
    private String dashboardNotificationMd;

    @ApiModelProperty(value = "仪表盘通知【用于首次登录弹框】")
    private String dashboardNotification;

    @ApiModelProperty(value = "搜索模式【0:SQL搜索 、1：全文检索】")
    private Integer searchModel;

    @ApiModelProperty(value = "邮箱地址")
    private String emailHost;

    @ApiModelProperty(value = "邮箱发件人")
    private String emailUsername;

    @ApiModelProperty(value = "邮箱授权码")
    private String emailPassword;

    @ApiModelProperty(value = "邮箱发送端口")
    private Integer emailPort;

    @ApiModelProperty(value = "启用邮箱发送")
    private Integer openEmail;

    @ApiModelProperty(value = "本地文件地址")
    private String localFileUrl;

    @ApiModelProperty(value = "文件上传方式 1:本地 2：七牛云")
    private Integer fileUploadWay;

    @ApiModelProperty(value = "阿里云ak")
    private String aliYunAccessKey;

    @ApiModelProperty(value = "阿里云sk")
    private String aliYunSecretKey;

    @ApiModelProperty(value = "阿里云存储桶名")
    private String aliYunBucket;

    @ApiModelProperty(value = "阿里云Endpoint")
    private String aliYunEndpoint;


}
