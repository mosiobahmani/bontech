package com.example.ServiceManage.repositories;

import com.example.ServiceManage.models.Services;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ServicesRepository extends GenericRepository<Services, Long>, JpaSpecificationExecutor<Services> {
}
