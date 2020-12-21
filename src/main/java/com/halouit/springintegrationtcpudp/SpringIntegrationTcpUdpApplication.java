package com.halouit.springintegrationtcpudp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringIntegrationTcpUdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringIntegrationTcpUdpApplication.class, args);
    }

}
