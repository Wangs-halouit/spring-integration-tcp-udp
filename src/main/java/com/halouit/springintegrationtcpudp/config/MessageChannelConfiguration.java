package com.halouit.springintegrationtcpudp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;

/**
 * author: wjg
 * date: 2020/12/18
 */
@Configuration
public class MessageChannelConfiguration {
    public static final String TCP_OUTPUT_CHANNEL_NAME = "tcpOutputChannel";
    public static final String TCP_INPUT_CHANNEL_NAME = "tcpInputChannel";
    @Bean(name = TCP_OUTPUT_CHANNEL_NAME)
    public MessageChannel tcpOutputChannel() {
        return new DirectChannel();
    }
    @Bean(name = TCP_INPUT_CHANNEL_NAME )
    public MessageChannel tcpInputChannel(){
        return new DirectChannel();
    }
}
