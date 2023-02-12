package com.gsoy.springdemo.model;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class Log extends BaseLog {

    private String logNo;
    private Date logTar;
    private String servisAdi;
    private String yosKod;
    private String hhsKod;
    private String groupId;
    private String requestId;
    private String psuInitiated;
    private String httpKod;
    private String request;
    private String response;
    private String hataAck;
    private Date tar;
    private Integer sicilno;

    public void setHttpKod(Integer httpKod) {
        this.httpKod = String.valueOf(httpKod);
    }

    public void setHttpKod(String httpKod) {
        this.httpKod = httpKod;
    }

}