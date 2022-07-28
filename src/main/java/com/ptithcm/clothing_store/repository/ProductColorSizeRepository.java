package com.ptithcm.clothing_store.repository;

import com.ptithcm.clothing_store.model.entity.ProductColorSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface  ProductColorSizeRepository extends JpaRepository<ProductColorSize,Long>, QuerydslPredicateExecutor<ProductColorSize> {
}
