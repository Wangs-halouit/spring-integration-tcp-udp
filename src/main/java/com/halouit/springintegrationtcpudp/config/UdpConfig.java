package com.halouit.springintegrationtcpudp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ip.udp.MulticastSendingMessageHandler;

/**
 * author: wjg
 * date: 2020/12/17
 */
@Configuration
public class UdpConfig {
    @Value("${udp.port}")
    private Integer port;
    @Value("${udp.ip}")
    private String ip;
    //配置出站
    @Bean
    @ServiceActivator(inputChannel = "udpOut")
    public MulticastSendingMessageHandler handler() {
        return new MulticastSendingMessageHandler(ip, port);
    }

}
