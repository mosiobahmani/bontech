package com.example.ServiceManage.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class BaseCriteria {

    private static final int PAGE_SIZE = 75;
    private String sortBy;
    private Integer startRow;
    private Integer endRow;
    private Integer page;
    private Integer pageSize;
    private String criteria;

    public Integer getPage() {
        return endRow != null && startRow != null ? (startRow / (endRow - startRow)) : 0;
    }

    public Integer getPageSize() {
        return endRow != null && startRow != null ? endRow - startRow : PAGE_SIZE;
    }

    public Sort getSort() {
        if (sortBy == null) {
            return Sort.by(Sort.Direction.ASC, "id");
        }
        boolean isAscending = !getSortBy().contains("-");
        String field = getSortBy().replace("-", "");
        return Sort.by(isAscending ? Sort.Direction.ASC : Sort.Direction.DESC, field);
    }

    public PageRequest getPageRequest() {

        if (sortBy != null) {
            return PageRequest.of(
                    getPage(),
                    getPageSize(),
                    getSort()
            );
        } else {
            return PageRequest.of(
                    getPage(),
                    getPageSize()
            );
        }
    }

    public List<AdvancedCriteria> getAdvancedCriteria() {
        AdvancedCriteria[] advancedCriteria = new AdvancedCriteria[0];
        if (criteria != null) {
            String tempCriteria = criteria;
            if (!criteria.contains("["))
                tempCriteria = "[" + criteria + "]";
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                advancedCriteria = objectMapper.readValue(tempCriteria, AdvancedCriteria[].class);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return Arrays.asList(advancedCriteria);
    }

    public Integer getEndRow() {
        return endRow != null ? endRow : 75;
    }

    public Integer getStartRow() {
        return startRow != null ? startRow : 0;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public void setEndRow(Integer endRow) {
        this.endRow = endRow;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
}
