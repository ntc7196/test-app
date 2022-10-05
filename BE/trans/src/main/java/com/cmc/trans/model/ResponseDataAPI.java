package com.cmc.trans.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseDataAPI {

    private String message;
    private Object data;

    public ResponseDataAPI(String message, String list) {
        this.message = message;
    }

    public <T> ResponseDataAPI(String message,T data) {
        this.message = message;
        this.data = data;
    }

    public <T> ResponseDataAPI(T data) {
        this.data = data;
    }

    public <T> ResponseDataAPI(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
