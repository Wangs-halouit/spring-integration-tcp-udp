package com.halouit.springintegrationtcpudp.udp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.halouit.springintegrationtcpudp.dto.UdpDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.ip.udp.MulticastSendingMessageHandler;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * author: wjg
 * date: 2020/12/17
 */
@Slf4j
@Component
public class UdpMulticastServer {
    @Value("${udp.port}")
    private String port;
    @Autowired
    private MulticastSendingMessageHandler messageHandler;


    @ServiceActivator(inputChannel = "udpOut")
    @Scheduled(fixedDelayString = "10000")
    public  void sendMessage(){
        UdpDto udpDto = new UdpDto("tcp-server-broadcast",port);
        try {
            messageHandler.handleMessage(MessageBuilder.withPayload(new ObjectMapper().writeValueAsString(udpDto)).build());
        } catch (JsonProcessingException e) {
           log.error("udp发送消息序列化异常",e);
        }
//        log.info("发送消息执行");
    }

}
