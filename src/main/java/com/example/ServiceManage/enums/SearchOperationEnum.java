package com.example.ServiceManage.enums;

public enum SearchOperationEnum {

    EQUAL("equals"),
    NOT_EQUAL("notEqual"),
    CONTAIN("contains"),
    NOT_CONTAIN("notContains"),
    MATCH_END("endsWith"),
    MATCH_START("startsWith"),
    GREATER_THAN("greaterThan"),
    GREATER_THAN_EQUAL("greaterOrEqual"),
    LESS_THAN("lessThan"),
    LESS_THAN_EQUAL("lessOrEqual");

    private final String mType;

    SearchOperationEnum(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }
}
