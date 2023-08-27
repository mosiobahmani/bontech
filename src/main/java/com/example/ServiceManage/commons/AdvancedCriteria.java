package com.example.ServiceManage.commons;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class AdvancedCriteria implements Serializable {

    @JsonProperty("fieldName")
    private String key;
    private String operator;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}