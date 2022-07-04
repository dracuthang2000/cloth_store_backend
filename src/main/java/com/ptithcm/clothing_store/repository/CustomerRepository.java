package com.ptithcm.clothing_store.repository;

import com.ptithcm.clothing_store.model.entity.Customer;
import com.ptithcm.clothing_store.repository.custom.CustomerRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, QuerydslPredicateExecutor<Customer>, CustomerRepositoryCustom {
    Customer findByAccountCustomer_Username(String username);
}
