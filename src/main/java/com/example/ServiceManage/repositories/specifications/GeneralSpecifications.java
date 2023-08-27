package com.example.ServiceManage.repositories.specifications;

import com.example.ServiceManage.commons.AdvancedCriteria;
import com.example.ServiceManage.enums.SearchOperationEnum;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GeneralSpecifications<T> {

    //searching by entity attribute through generic ,so we don't need to change code for new entity attribute searching each time
    public Specification<T> buildPredicates(List<AdvancedCriteria> criteria) {
        return (root, cq, cb) -> {

            List<Predicate> predicates = new ArrayList<>();
            Set<Attribute<? super T, ?>> attributes = root.getModel().getAttributes();

            // remove join properties from list. (search by join parameter do in specific specification)
            attributes.removeIf(Attribute::isAssociation);

            for (AdvancedCriteria myCriteria : criteria) {
                for (Attribute att : attributes) {
                    if (myCriteria.getKey().equals(att.getName()) && !att.getJavaType().isEnum()) {
                        predicates.add(
                                buildPredicate(
                                        root,
                                        cb,
                                        myCriteria.getOperator(),
                                        myCriteria.getKey(),
                                        myCriteria.getValue()
                                )
                        );
                    }
                }
            }
            return cb.and(predicates.toArray(new Predicate[0]));

        };
    }

    protected Predicate buildPredicate(Root<T> root, CriteriaBuilder cb, String operator, String key, String value) {

        if (operator.equals(SearchOperationEnum.EQUAL.getType())) {
            return getEqualPredicate(root, cb, key, value);
        } else if (operator.equals(SearchOperationEnum.NOT_EQUAL.getType())) {
            return getNotEqualPredicate(root, cb, key, value);
        } else if (operator.equals(SearchOperationEnum.CONTAIN.getType())) {
            return getContainPredicate(root, cb, key, value);
        } else if (operator.equals(SearchOperationEnum.NOT_CONTAIN.getType())) {
            return getNotContainPredicate(root, cb, key, value);
        } else if (operator.equals(SearchOperationEnum.MATCH_END.getType())) {
            return getMatchEndPredicate(root, cb, key, value);
        } else if (operator.equals(SearchOperationEnum.MATCH_START.getType())) {
            return getMatchStartPredicate(root, cb, key, value);
        } else if (operator.equals(SearchOperationEnum.GREATER_THAN.getType())) {
            return getGreaterThanPredicate(root, cb, key, value);
        } else if (operator.equals(SearchOperationEnum.GREATER_THAN_EQUAL.getType())) {
            return getGreaterThanEqualPredicate(root, cb, key, value);
        } else if (operator.equals(SearchOperationEnum.LESS_THAN.getType())) {
            return getLessThanPredicate(root, cb, key, value);
        } else if (operator.equals(SearchOperationEnum.LESS_THAN_EQUAL.getType())) {
            return getLessThanEqualPredicate(root, cb, key, value);
        } else
            return getEqualPredicate(root, cb, key, value);
    }

    private Predicate getEqualPredicate(Root<T> root, CriteriaBuilder cb, String key, String value) {
        return cb.equal(
                root.get(key),
                value);
    }

    private Predicate getNotEqualPredicate(Root<T> root, CriteriaBuilder cb, String key, String value) {
        return cb.notEqual(
                root.get(key),
                value);
    }

    private Predicate getContainPredicate(Root<T> root, CriteriaBuilder cb, String key, String value) {
        return cb.like(
                root.get(key),
                "%" + value + "%");
    }

    private Predicate getNotContainPredicate(Root<T> root, CriteriaBuilder cb, String key, String value) {
        return cb.not(
                root.get(key)).in(value);
    }

    private Predicate getMatchEndPredicate(Root<T> root, CriteriaBuilder cb, String key, String value) {
        return cb.like(
                root.get(key),
                value + "%");
    }

    private Predicate getMatchStartPredicate(Root<T> root, CriteriaBuilder cb, String key, String value) {
        return cb.like(
                root.get(key),
                "%" + value);
    }

    private Predicate getGreaterThanPredicate(Root<T> root, CriteriaBuilder cb, String key, String value) {
        return cb.greaterThan(
                root.get(key), value);
    }

    private Predicate getGreaterThanEqualPredicate(Root<T> root, CriteriaBuilder cb, String key, String value) {
        return cb.greaterThanOrEqualTo(
                root.get(key), value);
    }

    private Predicate getLessThanPredicate(Root<T> root, CriteriaBuilder cb, String key, String value) {
        return cb.lessThan(
                root.get(key), value);
    }

    private Predicate getLessThanEqualPredicate(Root<T> root, CriteriaBuilder cb, String key, String value) {
        return cb.lessThanOrEqualTo(
                root.get(key), value);
    }
}
