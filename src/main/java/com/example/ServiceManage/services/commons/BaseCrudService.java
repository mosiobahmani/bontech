package com.example.ServiceManage.services.commons;

import com.example.ServiceManage.commons.BaseCriteria;
import com.example.ServiceManage.commons.SmartResponse;

public interface BaseCrudService<INPUT, OUTPUT> {

    SmartResponse findByCriteria(BaseCriteria baseCriteria) throws Exception;

    OUTPUT findOne(Long id) throws Exception;

    void save(INPUT body) throws Exception;
}
