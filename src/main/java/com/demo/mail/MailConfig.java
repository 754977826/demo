package com.demo.mail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author nieyawei
 * @version 1.0
 * @className: MailConfig
 * @description:
 * @date 2019-06-09 18:18
 */
@Data
@Component
@ConfigurationProperties(prefix = "mail")
public class MailConfig {
    private String from;
    private String to;
}
