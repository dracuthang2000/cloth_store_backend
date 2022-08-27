package com.ptithcm.clothing_store.repository;

import com.ptithcm.clothing_store.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long>, QuerydslPredicateExecutor<Brand> {
}
