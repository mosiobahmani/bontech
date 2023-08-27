package com.example.ServiceManage.services;

import com.example.ServiceManage.models.Services;
import com.example.ServiceManage.models.User;
import com.example.ServiceManage.services.commons.BaseCrudService;
import org.javatuples.Pair;

public interface UserServiceService<INPUT, OUTPUT> extends BaseCrudService<INPUT, OUTPUT> {

    public Pair<User, Services> findingUserAndServiceById(Long userId, Long serviceId) throws Exception;
}
