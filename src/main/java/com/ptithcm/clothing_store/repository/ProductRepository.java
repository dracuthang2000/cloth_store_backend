package com.ptithcm.clothing_store.repository;

import com.ptithcm.clothing_store.model.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, QuerydslPredicateExecutor<Product> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "graph-product")
    List<Product> findAll();
}
