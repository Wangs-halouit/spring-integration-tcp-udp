package com.halouit.springintegrationtcpudp.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ip.tcp.TcpReceivingChannelAdapter;
import org.springframework.integration.ip.tcp.TcpSendingMessageHandler;
import org.springframework.integration.ip.tcp.connection.*;
import org.springframework.messaging.MessageChannel;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * author: wjg
 * date: 2020/12/18
 */
@Configuration
public class TcpConfiguration {
    @Value("${tcp.port}")
    private Integer port ;
    @Value("${tcp.ip}")
    private String serverIp ;
    @Bean
    public TcpNetServerConnectionFactory tcpNetServerConnectionFactory(){
        //系统默认使用了ByteArrayCrLfSerializer \r\n,可不再设置序列化
        return new TcpNetServerConnectionFactory(port);
    }



    @Bean
    public TcpNetClientConnectionFactory tcpNetClientConnectionFactory(){
       return new TcpNetClientConnectionFactory(serverIp, port);
    }

    @Bean
    public TcpReceivingChannelAdapter inBound(@Qualifier(MessageChannelConfiguration.TCP_OUTPUT_CHANNEL_NAME)MessageChannel messageChannel){
        TcpReceivingChannelAdapter adapter = new TcpReceivingChannelAdapter();
        adapter.setConnectionFactory(tcpNetServerConnectionFactory());
        adapter.setOutputChannel(messageChannel);
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = MessageChannelConfiguration.TCP_OUTPUT_CHANNEL_NAME)
    public TcpSendingMessageHandler tcpSendingMessageHandler(TcpNetServerConnectionFactory tcpNetServerConnectionFactory){
        TcpSendingMessageHandler messageHandler = new TcpSendingMessageHandler();
        messageHandler.setConnectionFactory(tcpNetServerConnectionFactory);
        return messageHandler;
    }
    /**
     * 存放客户端id
     */
    @Bean
    public Set<String> connectionIds(){
        return Collections.synchronizedSet(new HashSet<>());
    }
}
