package com.example.ServiceManage.repositories.specifications;

import com.example.ServiceManage.commons.AdvancedCriteria;
import com.example.ServiceManage.dtos.UserServicesOutput;
import com.example.ServiceManage.dtos.UserServicesOutput_INFO;
import com.example.ServiceManage.enums.YesNo;
import com.example.ServiceManage.models.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserServicesSpecification extends GeneralSpecifications<UserServices> {

    @Override
    public Specification<UserServices> buildPredicates(List<AdvancedCriteria> criterias) {

        Specification<UserServices> generalSpecification = super.buildPredicates(criterias);

        Specification<UserServices> selectionFields = (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            Join<UserServices, User> userJoin = root.join(UserServices_.USER, JoinType.LEFT);
            Join<UserServices, Services> serviceJoin = root.join(UserServices_.SERVICE, JoinType.LEFT);

            cq.multiselect(
                    root.get(UserServices_.ID),
                    userJoin.get(User_.ID),
                    serviceJoin.get(Services_.ID),
                    root.get(UserServices_.CAPACITY),
                    root.get(UserServices_.START_DATE_TIME),
                    root.get(UserServices_.END_DATE_TIME),
                    root.get(UserServices_.IS_ACTIVE),
                    userJoin.get(User_.USERNAMGE),
                    userJoin.get(User_.CREDIT),
                    serviceJoin.get(Services_.NAME),
                    serviceJoin.get(Services_.COST),
                    serviceJoin.get(Services_.IS_ACTIVE)
            );


                // when we want to search on data which they are not in our root generalSpecification support them, here just includes join properties
                criterias.forEach(criteria -> {


                    if (criteria.getKey().equals(UserServicesOutput_INFO.serviceName.getName())) {
                        predicates.add(
                                cb.like(
                                        cb.lower(serviceJoin.get(Services_.NAME)),
                                        "%" + criteria.getValue() + "%")
                        );
                    }  else if (criteria.getKey().equals(UserServicesOutput_INFO.cost.getName())) {
                        predicates.add(
                                cb.equal(
                                        serviceJoin.get(Services_.COST),
                                        criteria.getValue())
                        );
                    } else if (criteria.getKey().equals(UserServicesOutput_INFO.isMainServiceActive.getName())) {
                        predicates.add(
                                cb.equal(
                                        serviceJoin.get(Services_.IS_ACTIVE),
                                        YesNo.fromType(criteria.getValue()) )
                        );
                    } else if (criteria.getKey().equals(UserServicesOutput_INFO.serviceId.getName())) {
                        predicates.add(
                                cb.equal(
                                        serviceJoin.get(Services_.ID),
                                        criteria.getValue())
                        );
                    }else if (criteria.getKey().equals(UserServicesOutput_INFO.userId.getName())) {
                        predicates.add(
                                cb.equal(
                                        userJoin.get(User_.ID),
                                        criteria.getValue())
                        );
                    }else if (criteria.getKey().equals(UserServicesOutput_INFO.username.getName())) {
                        predicates.add(
                                cb.equal(
                                        userJoin.get(User_.USERNAMGE),
                                        criteria.getValue())
                        );
                    } else if (criteria.getKey().equals(UserServicesOutput_INFO.credit.getName())) {
                        predicates.add(
                                cb.equal(
                                        userJoin.get(User_.CREDIT),
                                        criteria.getValue())
                        );
                    }
                });
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return Specification.where(generalSpecification).and(selectionFields);
    }

    public static Specification<UserServices> userHasDuplicateServicePeriod(Long userId, Long serviceId, LocalDate startDate, LocalDate endDate) {
        return (root, cq, cb) -> {
            Join<UserServices, User> userJoin = root.join(UserServices_.USER, JoinType.LEFT);
            Join<UserServices, Services> servicesJoin = root.join(UserServices_.SERVICE, JoinType.LEFT);


            List<Predicate> predicates = new ArrayList<>();

            Predicate startDayChecking = cb.between(root.get(UserServices_.START_DATE), startDate, endDate);
            Predicate endDateChecking = cb.between(root.get(UserServices_.END_DATE), startDate, endDate);// for checking edge of days
            Predicate serviceIdPredicate = cb.equal(servicesJoin.get(Services_.ID), serviceId);
            Predicate userIdPredicate = cb.equal(userJoin.get(User_.ID), userId);

            Predicate predicateForColor = cb.or(startDayChecking, endDateChecking);

            predicates.add(predicateForColor);
            predicates.add(serviceIdPredicate);
            predicates.add(userIdPredicate);

            cq.multiselect(root.get(Services_.ID));


            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

/*    public static Specification<UserServices> serviceTimeConsumed(Long userId, Long serviceId){
        return (root, cq, cb) -> {
            //CriteriaQuery<Time> query = cb.createQuery(Time.class);
            //Root<MyEntity> root = query.from(MyEntity.class);
            Join<UserServices, User> userJoin = root.join(UserServices_.USER, JoinType.LEFT);
            Join<UserServices, Services> serviceJoin = root.join(UserServices_.SERVICE, JoinType.LEFT);

            cb.and(cb.equal(userJoin.get(User_.ID), userId), cb.equal(serviceJoin.get(Services_.ID), serviceId));

            Expression<Integer> diff = cb.diff(
                    cb.t(root.get("endDate")),
                    cb.toSeconds(root.get("startDate"))
            );

            Expression<Integer> sumDiff = criteriaBuilder.sum(diff);

            query.select(sumDiff);

            Expression<Double> diff = cb.diff(root.get(UserServices_.END_DATE_TIME), root.get(UserServices_.START_DATE_TIME));
            Expression<Double> sum = cb.sum(diff);

            cq.select(sum.alias("kooft"));


            return cb.diif
        };
    }*/

}
