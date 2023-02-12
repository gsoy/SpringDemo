package com.gsoy.springdemo.model;

import java.util.Date;

public class BaseLog {

    private String appName;
    private String currentServiceHost;
    private Date requestDate;
    private String responseData;
    private String insertionDate;
    private String callerId;
    private String callerIpAddres;
    private StringBuilder requestDataBuilder = new StringBuilder();

    public BaseLog() {
    }

    public String getCallerId() {
        return this.callerId;
    }

    public void setCallerId(String callerId) {
        this.callerId = callerId;
    }

    public String getCallerIpAddres() {
        return this.callerIpAddres;
    }

    public void setCallerIpAddres(String callerIpAddres) {
        this.callerIpAddres = callerIpAddres;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getCurrentServiceHost() {
        return this.currentServiceHost;
    }

    public void setCurrentServiceHost(String currentServiceHost) {
        this.currentServiceHost = currentServiceHost;
    }

    public Date getRequestDate() {
        return this.requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public String getResponseData() {
        return this.responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public String getInsertionDate() {
        return this.insertionDate;
    }

    public void setInsertionDate(String insertionDate) {
        this.insertionDate = insertionDate;
    }

    public String getRequestData() {
        return this.requestDataBuilder.toString();
    }

    public void appendRequestData(String data) {
        this.requestDataBuilder.append(data).append("\n");
    }
}