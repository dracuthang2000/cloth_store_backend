package com.ptithcm.clothing_store.repository;

import com.ptithcm.clothing_store.model.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, QuerydslPredicateExecutor<Product> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "graph-product")
    List<Product> findAll();
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "graph-product")
    Optional<Product> findById(Long id);
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "graph-product")
    List<Product> findAllByIsNew(Boolean isNew);
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "graph-product")
    Optional<Product> findByTag(String tag);
}
