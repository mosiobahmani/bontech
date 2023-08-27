package com.example.ServiceManage.repositories;

import com.example.ServiceManage.models.UserServices;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;

@Repository
public interface UserServiceRepository extends GenericRepository<UserServices, Long>, JpaSpecificationExecutor<UserServices> {

    @Query(value = "SELECT  sum(timediff(end_date_time ,start_date_time )) as consumedTime  FROM TBL_USER_SERVICE where user_id = ?1 and service_id = ?2 " ,nativeQuery = true)
     Double consumedServiceTime(Long userId, Long serviceId);
}
