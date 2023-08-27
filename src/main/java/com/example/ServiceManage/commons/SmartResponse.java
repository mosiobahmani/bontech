package com.example.ServiceManage.commons;

public class SmartResponse<T> {

    private Integer startRow;
    private Integer endRow;
    private Long totalRows;

    private T data;

    public SmartResponse(Integer startRow, Integer endRow, Long totalRows, T data) {
        this.startRow = startRow;
        this.endRow = endRow;
        this.totalRows = totalRows;
        this.data = data;
    }

    public SmartResponse() {
    }

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getEndRow() {
        return endRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public Long getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Long totalRows) {
        this.totalRows = totalRows;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
