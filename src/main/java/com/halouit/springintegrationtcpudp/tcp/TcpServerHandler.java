package com.halouit.springintegrationtcpudp.tcp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.halouit.springintegrationtcpudp.dto.TcpDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.ip.IpHeaders;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * author: wjg
 * date: 2020/12/18
 */
@Slf4j
@Component
public class TcpServerHandler {
    @Autowired
    private TcpMessageListener tcpMessageListener;
    @Autowired
    private MessageChannel tcpOutputChannel;
    //测试使用
    @Scheduled(fixedDelay = 2000L)
    public void send() {
        TcpDto tcpDto = new TcpDto("update-background", "http://xxx.xxxx.xx");
        try {
            String msg = new ObjectMapper().writeValueAsString(tcpDto);

            Set<String> connections = tcpMessageListener.connectionIds;
            for (String connectionId : connections) {
                log.info("服务端发送信息给客户端id:{}", connectionId);
                Message<String> message = MessageBuilder.withPayload(msg)
                        .setHeader(IpHeaders.CONNECTION_ID, connectionId)
                        .build();
                tcpOutputChannel.send(message);
            }
        } catch (JsonProcessingException e) {
            log.error("tcp消息序列化异常", e);
        }
    }
}
