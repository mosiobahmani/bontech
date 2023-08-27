package com.example.ServiceManage.models;

import com.example.ServiceManage.dtos.UserServicesInput;
import com.example.ServiceManage.dtos.UserServicesOutput;
import com.example.ServiceManage.enums.YesNo;
import com.example.ServiceManage.enums.YesNoConverter;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TBL_USER_SERVICE", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"SERVICE_ID", "USER_ID", "START_DATE"})
})
public class UserServices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @JoinColumn(name = "User_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(name = "Service_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Services service;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "START_DATE")
    private LocalDate startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "START_DATE_TIME")
    private LocalDateTime startDateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "END_DATE")
    private LocalDate endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "END_DATE_TIME")
    private LocalDateTime endDateTime;

    @Column(name = "CAPACITY")
    private Long capacity;
    @Column(name = "IS_ACTIVE")
    @Convert(converter = YesNoConverter.class)
    private YesNo isActive;

    public void fromDto(UserServicesInput input) {
        this.id = input.getId();
        this.startDate = input.getStartDateTime().toLocalDate();
        this.startDateTime = input.getStartDateTime();
        this.endDate = input.getEndDateTime().toLocalDate();
        this.endDateTime = input.getEndDateTime();
        this.capacity = input.getCapacity();
    }

    public UserServicesOutput toDto() {
        UserServicesOutput output = new UserServicesOutput();

        //output.set
        return output;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public Long getCapacity() {
        return capacity;
    }

    public void setCapacity(Long capacity) {
        this.capacity = capacity;
    }

    public YesNo getIsActive() {
        return isActive;
    }

    public void setIsActive(YesNo isActive) {
        this.isActive = isActive;
    }
}
