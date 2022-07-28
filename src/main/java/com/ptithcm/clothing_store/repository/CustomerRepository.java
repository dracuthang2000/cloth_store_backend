package com.ptithcm.clothing_store.repository;

import com.ptithcm.clothing_store.model.entity.Customer;
import com.ptithcm.clothing_store.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer>, CustomerRepositoryCustom {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,value = "customer_graphic")
    Customer findByAccountCustomer_Username(String username);
}
