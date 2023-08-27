package com.example.ServiceManage.dtos;

import com.example.ServiceManage.commons.BaseDto;
import com.example.ServiceManage.enums.YesNo;
import io.github.mhagnumdw.beaninfogenerator.GenerateBeanMetaInfo;

import java.time.LocalDateTime;

@GenerateBeanMetaInfo
public class UserServicesOutput extends BaseDto {

    private Long id;
    private Long userId;
    private Long serviceId;
    private Long capacity;
    private Long credit;
    private Long cost;
    private String username;
    private String serviceName;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private YesNo isMainServiceActive;
    private YesNo isActiveForUser;

    private YesNo isOnPeriod;

    public UserServicesOutput() {
    }

    // this construct is uesd for data table fetch
    public UserServicesOutput(Long id ,Long userId, Long serviceId, Long capacity,
                              LocalDateTime startDateTime, LocalDateTime endDateTime,
                              String serviceName, YesNo isMainServiceActive, String username, Long credit,  Long cost, YesNo isActiveForUser) {
        this.id = id;
        this.userId = userId;
        this.serviceId = serviceId;
        this.capacity = capacity;
        this.credit = credit;
        this.username = username;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.isMainServiceActive = isMainServiceActive;
        this.isActiveForUser = isActiveForUser;
        this.serviceName = serviceName;
        this.cost = cost;
    }


    public UserServicesOutput(Long Id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDate) {
        this.startDateTime = startDate;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public YesNo getIsMainServiceActive() {
        return isMainServiceActive;
    }

    public void setIsMainServiceActive(YesNo isMainServiceActive) {
        this.isMainServiceActive = isMainServiceActive;
    }

    public YesNo getIsActiveForUser() {
        return isActiveForUser;
    }

    public void setIsActiveForUser(YesNo isActiveForUser) {
        this.isActiveForUser = isActiveForUser;
    }

    public YesNo getIsOnPeriod() {
        return isOnPeriod;
    }

    public void setIsOnPeriod(YesNo isOnPeriod) {
        this.isOnPeriod = isOnPeriod;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
