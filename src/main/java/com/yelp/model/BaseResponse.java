package com.yelp.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseResponse {

    private String statusMessage;
    private Integer statusCode;


    @JsonProperty("return_message")
    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    @JsonProperty("return_code")
    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
