package com.budu.config;

import com.budu.quartz.job.ZhiYaCrawlerJob;

import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;


/**
 * quartz 配置类
 */
@Configuration
public class QuartzConfig {

    @Autowired
    private JobFactory jobFactory;

    //1、创建 Job 对象
    @Bean
    public JobDetailFactoryBean jobDetailFactoryBean(){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        //通过反射的方式实例化 Job,并没有经过 spring 处理，所以依赖的对象不能通过 autowrite 注入
        jobDetailFactoryBean.setJobClass(ZhiYaCrawlerJob.class);
        return jobDetailFactoryBean;
    }

    //2、创建 Trigger 对象：也是分为两种:下面是创建一个简单的 trigger
    //这里需要传入 JOB 对象，作为参数关联
//   @Bean
//    public SimpleTriggerFactoryBean simpleTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean){
//        SimpleTriggerFactoryBean simpleTriggerFactoryBean = new SimpleTriggerFactoryBean();
//        //获取 JobDetail 关联
//        simpleTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
//        // 触发条件   该参数表示一个执行的毫秒数
//        simpleTriggerFactoryBean.setRepeatInterval(2000);
//        //设置重复次数
//        simpleTriggerFactoryBean.setRepeatCount(5);
//        return simpleTriggerFactoryBean;
//    }

    //    通过 cron 表达式表示执行 trigger
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(){
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        //获取 JobDetail 关联
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean().getObject());
        // 触发条件   该参数表示一个执行的毫秒数
        cronTriggerFactoryBean.setCronExpression("0 0 17 * * ?");
        return cronTriggerFactoryBean;
    }

    // 创建 scheduler 对象
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        //关联 Trigger
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean().getObject());
        schedulerFactoryBean.setJobFactory(jobFactory);
        return schedulerFactoryBean;
    }

    //启动时自动执行
    @Bean
    public ApplicationRunner applicationRunner(){
        return args -> {
            schedulerFactoryBean().getScheduler().scheduleJob(jobDetailFactoryBean().getObject(),cronTriggerFactoryBean().getObject());
        };
    }
}
