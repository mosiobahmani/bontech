package com.example.ServiceManage.services;

import com.example.ServiceManage.commons.AdvancedCriteria;
import com.example.ServiceManage.commons.BaseCriteria;
import com.example.ServiceManage.commons.SmartResponse;
import com.example.ServiceManage.dtos.UserServicesInput;
import com.example.ServiceManage.dtos.UserServicesOutput;
import com.example.ServiceManage.exceptions.BadRequestException;
import com.example.ServiceManage.exceptions.ResourceNotFoundException;
import com.example.ServiceManage.models.Services;
import com.example.ServiceManage.models.User;
import com.example.ServiceManage.models.UserServices;
import com.example.ServiceManage.repositories.ServicesRepository;
import com.example.ServiceManage.repositories.UserRepository;
import com.example.ServiceManage.repositories.UserServiceRepository;
import com.example.ServiceManage.repositories.specifications.UserServicesSpecification;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static com.example.ServiceManage.repositories.specifications.UserServicesSpecification.userHasDuplicateServicePeriod;

@Service
public class UserServiceServiceImpl implements UserServiceService<UserServicesInput, UserServicesOutput> {

    @Autowired
    UserServiceRepository userServiceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServicesRepository servicesRepository;

    @Value("${limit.time}")
    private Integer limitTime;

    @Override
    public SmartResponse findByCriteria(BaseCriteria baseCriteria) throws Exception {
        List<AdvancedCriteria> criterias = baseCriteria.getAdvancedCriteria();

        Page<UserServicesOutput> userServicesOutputs = userServiceRepository.findAll(new UserServicesSpecification().buildPredicates(criterias),
                baseCriteria.getPageRequest(), UserServicesOutput.class);

        return new SmartResponse<>(
                baseCriteria.getStartRow(),
                baseCriteria.getEndRow(),
                userServicesOutputs.getTotalElements(),
                userServicesOutputs.getContent()
        );
    }

    @Override
    public UserServicesOutput findOne(Long id) throws Exception {
        return null;
    }

    @Override
    @Transactional
    public void save(UserServicesInput body) throws Exception {
        UserServices userServices = new UserServices();
        _bodyValidation(body);
        userServices.fromDto(body);

        Pair<User, Services> pair = findingUserAndServiceById(body.getUserId(), body.getServiceId());
        userServices.setUser(pair.getValue0());
        userServices.setService(pair.getValue1());
        serviceDateAndTimeValidation(body);

        userServiceRepository.save(userServices);

    }

    private void _bodyValidation(UserServicesInput body) throws Exception {

        if (body.getCapacity() == null || body.getCapacity() == 0) {
            throw new BadRequestException("Capacity must have data bigger than 0");
        } else if (body.getServiceId() == null) {
            throw new BadRequestException("ServiceId is null");
        } else if (body.getUserId() == null) {
            throw new BadRequestException("userId is null");
        } else if (body.getStartDateTime() == null) {
            throw new BadRequestException("Start date is null");
        } else if (body.getEndDateTime() == null) {
            throw new BadRequestException("End date is null");
        }
        if (body.getStartDateTime().until(body.getEndDateTime(), ChronoUnit.HOURS) > limitTime) {
            throw new BadRequestException("your period time for each service must be less than: " + limitTime);
        }
    }

    public void serviceDateAndTimeValidation(UserServicesInput body) throws Exception {
        boolean isPresent = userServiceRepository.findOne(userHasDuplicateServicePeriod(body.getUserId(), body.getServiceId(),
                body.getStartDateTime().toLocalDate(), body.getEndDateTime().toLocalDate()), UserServicesOutput.class).isPresent();
        if (isPresent) {
            throw new BadRequestException(" duplicate Date for service;");
        }
        Double time = userServiceRepository.consumedServiceTime(body.getUserId(), body.getServiceId());

        if (time != null && (time / 10000) > limitTime) {
            throw new BadRequestException("Time capacity for this service has done ");
        }
    }

    public Pair<User, Services> findingUserAndServiceById(Long userId, Long serviceId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found by id: " + userId)
        );
        Services service = servicesRepository.findById(serviceId).orElseThrow(
                () -> new ResourceNotFoundException("Service not found by id: " + serviceId)
        );
        return new Pair<>(user, service);
    }

}
