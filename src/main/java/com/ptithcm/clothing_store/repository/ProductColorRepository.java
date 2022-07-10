package com.ptithcm.clothing_store.repository;

import com.ptithcm.clothing_store.model.entity.ProductColor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor,Long>, QuerydslPredicateExecutor<ProductColor> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "graph-product-color")
    Optional<ProductColor> findByIdOrderByProductColorSizeIdAsc(Long id);
}
