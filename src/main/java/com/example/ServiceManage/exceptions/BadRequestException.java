package com.example.ServiceManage.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception {

    private static final long serialVersionUID = 1L;

    private HashMap data;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, HashMap data) {
        super(message);
        this.data = data;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }
}
