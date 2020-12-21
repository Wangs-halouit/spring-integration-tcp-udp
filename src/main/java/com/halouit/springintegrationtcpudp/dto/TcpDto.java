package com.halouit.springintegrationtcpudp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TcpDto {
    private String type;
    private String url;
}
