package com.demo.mail.service;

/**
 * @author nieyawei
 * @version 1.0
 * @className: MailSSLSocketFactory
 * @description:
 * @date 2019-06-10 22:43
 */

import com.demo.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

/**
 * @Description: 邮件ssl
 * @author lc
 * @date 2018年5月31日
 */
@Slf4j
public class MailSSLSocketFactory extends SSLSocketFactory {
    private SSLSocketFactory factory;

    public MailSSLSocketFactory() {
        try {
            SSLContext sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[] { new MailTrustManager() }, null);
            factory = sslcontext.getSocketFactory();
        } catch (Exception e) {
            DateUtil.ERROR_COUNT++;
            log.error("MailSSLSocketFactory构造器异常！", e.getMessage());
        }
    }

    public static SocketFactory getDefault() {
        return new MailSSLSocketFactory();
    }

    @Override
    public Socket createSocket() throws IOException {
        return factory.createSocket();
    }

    @Override
    public Socket createSocket(Socket socket, String s, int i, boolean flag) throws IOException {
        return factory.createSocket(socket, s, i, flag);
    }

    @Override
    public Socket createSocket(InetAddress inaddr, int i, InetAddress inaddr1, int j) throws IOException {
        return factory.createSocket(inaddr, i, inaddr1, j);
    }

    @Override
    public Socket createSocket(InetAddress inaddr, int i) throws IOException {
        return factory.createSocket(inaddr, i);
    }

    @Override
    public Socket createSocket(String s, int i, InetAddress inaddr, int j) throws IOException {
        return factory.createSocket(s, i, inaddr, j);
    }

    @Override
    public Socket createSocket(String s, int i) throws IOException {
        return factory.createSocket(s, i);
    }

    @Override
    public String[] getDefaultCipherSuites() {
        return factory.getDefaultCipherSuites();
    }

    @Override
    public String[] getSupportedCipherSuites() {
        return factory.getSupportedCipherSuites();
    }
}

