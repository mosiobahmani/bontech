package com.example.ServiceManage.repositories;

import com.example.ServiceManage.models.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends GenericRepository<User, Long>, JpaSpecificationExecutor<User> {
}
