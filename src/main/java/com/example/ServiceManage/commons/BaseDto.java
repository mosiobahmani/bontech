package com.example.ServiceManage.commons;

import lombok.Data;

@Data
public class BaseDto {
    private static final long serialVersionUID = 1L;

    private String createdBy;
    private String createdAt;
    private String modifiedBy;
    private String modifiedAt;
    private String createdAtTime;

    public BaseDto() {
        super();
        // TODO Auto-generated constructor stub
    }
}
