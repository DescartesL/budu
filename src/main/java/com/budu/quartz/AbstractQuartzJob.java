package com.budu.quartz;

import com.budu.entity.Job;
import com.budu.entity.JobLog;
import com.budu.enums.ScheduleConstants;
import com.budu.mapper.JobLogMapper;
import com.budu.util.SpringUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import static com.budu.common.Constants.ONE;
import static com.budu.common.Constants.ZERO;

/**
 * @author blue
 * @date 2021/12/8
 * @apiNote 抽象quartz调用
 */
public abstract class AbstractQuartzJob implements org.quartz.Job {
    private static final Logger log = LoggerFactory.getLogger(AbstractQuartzJob.class);

    /**
     * 线程本地变量
     */
    private static final ThreadLocal<Date> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public void execute(JobExecutionContext context) {
        Job job = new Job();
        // 这段代码是在Quartz Job的执行方法中，通过JobExecutionContext获取任务属性，
        // 然后使用BeanUtils.copyProperties方法将任务属性复制到Job对象中。
        // 其中，context.getMergedJobDataMap().get(ScheduleConstants.TASKPROPERTIES)获取的是任务属性，而job是一个空的Job对象，用于存储任务属性。
        // 这样做的目的是为了在任务执行过程中方便地获取任务属性。
        BeanUtils.copyProperties(context.getMergedJobDataMap().get(ScheduleConstants.TASK_PROPERTIES), job);
        try {
            before(context, job);
            doExecute(context, job);
            after(context, job, null);
        } catch (Exception e) {
            log.error("任务执行异常  - ：", e);
            after(context, job, e);
        }
    }

    /**
     * 执行前
     *
     * @param context 工作执行上下文对象
     * @param job  系统计划任务
     */
    protected void before(JobExecutionContext context, Job job) {
        THREAD_LOCAL.set(new Date());
    }

    /**
     * 执行后
     *
     * @param context 工作执行上下文对象
     * @param job  系统计划任务
     */
    protected void after(JobExecutionContext context, Job job, Exception e) {
        Date startTime = THREAD_LOCAL.get();
        THREAD_LOCAL.remove();

        final JobLog jobLog = new JobLog();
        jobLog.setJobId(job.getJobId());
        jobLog.setJobName(job.getJobName());
        jobLog.setJobGroup(job.getJobGroup());
        jobLog.setInvokeTarget(job.getInvokeTarget());
        jobLog.setStartTime(startTime);
        jobLog.setStopTime(new Date());
        long runMs = jobLog.getStopTime().getTime() - jobLog.getStartTime().getTime();
        jobLog.setJobMessage(jobLog.getJobName() + " 总共耗时：" + runMs + "毫秒");
        if (e != null) {
            jobLog.setStatus(ONE);
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String str = sw.toString();
            String errorMsg = StringUtils.substring(str, 0, 2000);
            jobLog.setExceptionInfo(errorMsg);
        } else {
            jobLog.setStatus(ZERO);
        }

        // 写入数据库当中
        SpringUtils.getBean(JobLogMapper.class).insert(jobLog);
    }

    /**
     * 执行方法，由子类重载
     *
     * @param context 工作执行上下文对象
     * @param job  系统计划任务
     * @throws Exception 执行过程中的异常
     */
    protected abstract void doExecute(JobExecutionContext context, Job job) throws Exception;
}
