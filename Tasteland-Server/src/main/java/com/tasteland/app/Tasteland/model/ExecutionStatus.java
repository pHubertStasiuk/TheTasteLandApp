package com.tasteland.app.Tasteland.model;

import lombok.Data;

@Data
public class ExecutionStatus {

    private String code;
    private String message;
    private Object object;

    public ExecutionStatus(String code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.object = object;
    }

    public ExecutionStatus(String code, String message) {
        this.code = code;
        this.message = message;

    }
}
