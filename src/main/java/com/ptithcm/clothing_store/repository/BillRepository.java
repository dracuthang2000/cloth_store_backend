package com.ptithcm.clothing_store.repository;

import com.ptithcm.clothing_store.model.Enum.EnumState;
import com.ptithcm.clothing_store.model.entity.Bill;
import com.ptithcm.clothing_store.repository.custom.BillRepositoryCustom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long>, QuerydslPredicateExecutor<Bill>, BillRepositoryCustom {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "bill-graph")
    List<Bill> findAll();
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "bill-graph")
    Optional<Bill> findById(Long id);
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "bill-graph")
    List<Bill> findByCustomerId(Long id);
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "bill-graph")
    List<Bill> findAllByOrdersState(EnumState state);
}
