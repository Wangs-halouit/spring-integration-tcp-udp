package com.halouit.springintegrationtcpudp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UdpDto {
    private String type;
    private String tcpServerPort;
}
