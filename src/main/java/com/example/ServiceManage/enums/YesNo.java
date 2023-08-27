package com.example.ServiceManage.enums;

public enum YesNo {
    YES(1),
    NO(0);
    private final Integer value;

    YesNo(Integer value) {
        this.value = value;
    }

    public static YesNo fromValue(Integer value) {

        if (value == 1) {
            return YesNo.YES;
        } else {
            return YesNo.NO;
        }
    }

    public Integer toValue() {
        return value;
    }

    public static YesNo fromType(String type) {
        if (type.equals(YES.name())) {
            return YES;
        } else {
            return NO;
        }
    }
}