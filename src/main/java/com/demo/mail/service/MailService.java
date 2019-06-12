package com.demo.mail.service;

import com.demo.mail.MailConfig;
import com.demo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * @author nieyawei
 * @version 1.0
 * @className: MailService
 * @description:
 * @date 2019-06-09 18:19
 */
@Slf4j
@Service
public class MailService {
    @Autowired
    private MailConfig mailConfig;
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String subject, String text, Map<String, String> attachmentMap) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //是否发送的邮件是富文本（附件，图片，html等）
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);

        messageHelper.setFrom(mailConfig.getFrom());
        messageHelper.setTo(mailConfig.getTo());

        messageHelper.setSubject(subject);
        messageHelper.setText(text, true);//重点，默认为false，显示原始html代码，无效果

        if (attachmentMap != null) {
            attachmentMap.forEach((key, value) -> {
                try {
                    File file = new File(value);
                    if (file.exists()) {
                        messageHelper.addAttachment(file.getName(), new FileSystemResource(file));
                    }
                } catch (MessagingException e) {
                    DateUtil.ERROR_COUNT++;
                    log.info("发送邮件添加附件失败：{}", subject + ":" + text + attachmentMap.keySet(), e);
                }
            });
        }

        mailSender.send(mimeMessage);
    }

    public void sendSSLMail(String subject, String text, String filePath) {
        MimeMessage message = null;
        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mailConfig.getFrom());
            helper.setTo(mailConfig.getTo());

            helper.setSubject(subject);
            helper.setText(text, true);//重点，默认为false，显示原始html代码，无效果

            // 用流的形式发送附件邮件
            File file = new File(filePath);
            if (file.exists()) {
                DataSource source = new ByteArrayDataSource(new FileInputStream(file), "application/msexcel");
                helper.addAttachment(file.getName(), source);
            }
            mailSender.send(message);
        } catch (IOException | MessagingException e) {
            DateUtil.ERROR_COUNT++;
            log.error("发送带附件的邮件异常！", e.getMessage());
        }
    }


}
