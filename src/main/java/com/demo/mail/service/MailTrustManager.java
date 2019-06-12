package com.demo.mail.service;

/**
 * @author nieyawei
 * @version 1.0
 * @className: MailTrustManager
 * @description:
 * @date 2019-06-10 22:43
 */

import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * @Description: 邮箱信证管理
 * @author lc
 * @date 2018年5月31日
 */
public class MailTrustManager implements X509TrustManager {
    public void checkClientTrusted(X509Certificate[] cert, String authType) {
        // everything is trusted
    }

    public void checkServerTrusted(X509Certificate[] cert, String authType) {
        // everything is trusted
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
