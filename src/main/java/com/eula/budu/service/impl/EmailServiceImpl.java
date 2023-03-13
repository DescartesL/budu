package com.eula.budu.service.impl;

import com.eula.budu.common.RedisConstants;
import com.eula.budu.entity.SystemConfig;
import com.eula.budu.service.EmailService;
import com.eula.budu.service.ISystemConfigService;
import com.eula.budu.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailServiceImpl implements EmailService {
    
    private final RedisService redisService;

    private final ISystemConfigService systemConfigService;

    private final JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

    /**
     * 创建BEAN时初始化
     */
    @PostConstruct
    public void init(){
        SystemConfig systemConfig = systemConfigService.getCustomizeOne();
        javaMailSender.setHost(systemConfig.getEmailHost());
        javaMailSender.setUsername(systemConfig.getEmailUsername());
        javaMailSender.setPassword(systemConfig.getEmailPassword());
        javaMailSender.setPort(systemConfig.getEmailPort());
        javaMailSender.setDefaultEncoding("UTF-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.debug", "true");
        javaMailSender.setJavaMailProperties(p);
    }

    /**
     * 发送消息
     * @param email
     * @param template
     */
    private void send(String email, String template) throws MessagingException {
        // 创建一个mime消息
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        // 设置邮件主题
        mimeMessageHelper.setSubject("不蠹");
        // 设置邮件发送者
        mimeMessageHelper.setFrom(Objects.requireNonNull(javaMailSender.getUsername()));
        // 设置邮件接收者（可以有多个接收者，中间用逗号隔开）
        mimeMessageHelper.setTo(email);
        // 设置邮件发送日期
        mimeMessageHelper.setSentDate(new Date());
        mimeMessageHelper.setText(template);
        javaMailSender.send(mimeMessage);
    }

    /**
     * 发送邮箱验证码
     * @param email
     */
    @Override
    public void sendCode(String email) throws MessagingException {
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        String content = "感谢使用不蠹斋，您的验证码是：" + code + "  有效时间为5分钟。";
        send(email, content);
        log.info("邮箱验证码发送成功，邮箱：{}， 验证码：{}", email, code);
            
        redisService.setCacheObject(RedisConstants.EMAIL_CODE + email,code + "");
        redisService.expire(RedisConstants.EMAIL_CODE + email, RedisConstants.CAPTCHA_EXPIRATION, TimeUnit.MINUTES);
        
    }
}
