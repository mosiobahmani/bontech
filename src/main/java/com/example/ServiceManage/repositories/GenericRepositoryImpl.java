package com.example.ServiceManage.repositories;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
@NoRepositoryBean
public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements GenericRepository<T, ID>, Serializable {

    private static final long serialVersionUID = 1L;
    private final EntityManager em;


    private final JpaEntityInformation<T, ?> entityInformation;
    //private CrudMethodMetadata metadata;

    public GenericRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.em = em;
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, em);
    }


    public GenericRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager em) {
        super(entityInformation, em);
        this.entityInformation = entityInformation;
        this.em = em;

    }

    protected Class<T> getDomainClass() {
        return this.entityInformation.getJavaType();
    }

    private static boolean isUnpaged(Pageable pageable) {
        return pageable.isUnpaged();
    }

    public <S> Page<S> findAll(@Nullable Specification<T> spec, Pageable pageable, Class<S> projectionType) {
        TypedQuery<T> query = null;
        try {
            query = this.getQuery1(spec, projectionType, pageable);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (Page) (isUnpaged(pageable) ? new PageImpl(query.getResultList()) : this.readPage(query, getDomainClass(), pageable, spec));
    }

    @Override
    public <S> List<S> findAll(Specification<T> spec, Class<S> projectionType) throws Exception {
        return (List) this.getQueryWithoutPage(spec, projectionType, Sort.unsorted()).getResultList();
    }

    protected <S> TypedQuery<T> getQueryWithoutPage(@Nullable Specification<T> spec, Class<S> projectionType, Sort sort) throws ClassNotFoundException {
        return this.getQuery2(spec, projectionType, this.getDomainClass(), sort);
    }

    protected <S> TypedQuery<T> getQuery1(@Nullable Specification<T> spec, Class<S> projectionType, Pageable pageable) throws ClassNotFoundException {
        Sort sort = pageable.isPaged() ? pageable.getSort() : Sort.unsorted();
        return this.getQuery2(spec, projectionType, getDomainClass(), sort);
    }


    protected <S> TypedQuery<T> getQuery2(@Nullable Specification<T> spec, Class<S> projectionType, Class<T> domainClass, Sort sort) throws ClassNotFoundException {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<S> query = builder.createQuery(projectionType);
        Root<T> root = this.applySpecificationToCriteria(spec, domainClass, query);
        if (query.getSelection() != null) query.multiselect(query.getSelection().getCompoundSelectionItems());

        if (sort.isSorted()) {
            query.orderBy(QueryUtils.toOrders(sort, root, builder));
        }

        return (TypedQuery<T>) this.em.createQuery(query);
    }


    public <S extends T> S save(S entity) {
        if (this.entityInformation.isNew(entity)) {
            this.em.persist(entity);
            flush();
            return entity;
        }
        entity = this.em.merge(entity);
        flush();
        return entity;
    }


    private <S> Root<T> applySpecificationToCriteria(@Nullable Specification<T> spec, Class<T> domainClass, CriteriaQuery<S> query) {
        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(query, "CriteriaQuery must not be null!");
        Root<T> root = query.from(domainClass);
        if (spec == null) {
            return root;
        } else {
            CriteriaBuilder builder = this.em.getCriteriaBuilder();
            Predicate predicate = spec.toPredicate(root, query, builder);

            if (predicate != null) {
                query.where(predicate);
            }

            return root;
        }
    }

    @Override
    public <S> Optional<S> findOne(@Nullable Specification<T> spec, Class<S> projectionType) {
        try {
            return (Optional<S>) Optional.of(this.getQuery2(spec, projectionType, getDomainClass(), Sort.unsorted()).getSingleResult());

        } catch (NoResultException | ClassNotFoundException var3) {
            return Optional.empty();
        }
    }

}
