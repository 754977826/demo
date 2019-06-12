package com.demo.mail.schedule;

import com.demo.mail.service.MailService;
import com.demo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * @author nieyawei
 * @version 1.0
 * @className: MailScheduleTask
 * @description:
 * @date 2019-06-09 20:09
 */
@Slf4j
@Configuration
@EnableScheduling   //开启定时任务
public class MailScheduleTask {
    @Value("${spring.application.name}")
    private String appName;

    @Value("${log.path}")
    private String logPath;

    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 0 8,20 * * ?")
    private void sendLogTasks() {
        String logName = appName + "-" + DateUtil.getDate() + ".log";
        GregorianCalendar ca = new GregorianCalendar();
        String am_pm = "";
        if (ca.get(GregorianCalendar.AM_PM) == 0){
            am_pm = "上午8点";
        }else {
            am_pm = "晚上8点";
        }
        String subject = appName + "-" + DateUtil.getDate() + "-" +am_pm + "日志邮件";
        String text = "发生错误数量：" + DateUtil.ERROR_COUNT;
        String logFileName = logPath + logName;
        Map<String, String> fileMap = new HashMap<>();
        fileMap.put(logName, logFileName);
        mailService.sendSSLMail(subject, text, logFileName);
        log.info("日志已发送：{}", DateUtil.getTime());
    }

}
