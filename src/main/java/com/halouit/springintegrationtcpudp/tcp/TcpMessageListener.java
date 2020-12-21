package com.halouit.springintegrationtcpudp.tcp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.integration.ip.tcp.connection.TcpConnectionCloseEvent;
import org.springframework.integration.ip.tcp.connection.TcpConnectionEvent;
import org.springframework.integration.ip.tcp.connection.TcpConnectionOpenEvent;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * author: wjg
 * date: 2020/12/18
 */
@Slf4j
@Component
public class TcpMessageListener implements ApplicationListener<TcpConnectionEvent> {
    public final Set<String> connectionIds;
    public TcpMessageListener(Set<String> connectionIds){
        this.connectionIds = connectionIds;
    }

    @Override
    public void onApplicationEvent(TcpConnectionEvent event) {
        if(event instanceof TcpConnectionOpenEvent){
            connectionIds.add(event.getConnectionId());
            log.info("----客户端id: {} 加入连接-----",event.getConnectionId());
        }else if(event instanceof TcpConnectionCloseEvent){
            connectionIds.remove(event.getConnectionId());
            log.info("----客户端id: {} 关闭连接-----",event.getConnectionId());
        }
    }
}
