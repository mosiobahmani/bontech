package com.example.ServiceManage.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    <S> Page<S> findAll(@Nullable Specification<T> spec, Pageable pageable, Class<S> projectionType);

    <S> List<S> findAll(@Nullable Specification<T> spec, Class<S> projectionType) throws Exception;

    <S> Optional<S> findOne(@Nullable Specification<T> spec, Class<S> projectionType);

}