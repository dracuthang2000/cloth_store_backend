package com.ptithcm.clothing_store.repository;

import com.ptithcm.clothing_store.model.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long>, QuerydslPredicateExecutor<Orders> {
    Orders findByBillId(Long id);
}
