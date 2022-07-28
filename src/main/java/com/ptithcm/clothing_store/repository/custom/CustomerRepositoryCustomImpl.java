package com.ptithcm.clothing_store.repository.custom;

import com.ptithcm.clothing_store.model.entity.Customer;
import com.ptithcm.clothing_store.model.entity.QCustomer;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CustomerRepositoryCustomImpl implements CustomerRepositoryCustom {

    @Autowired
    @PersistenceContext
    private EntityManager em;

    @Override
    public Customer findCustomerByUsername(String username) {
        return new JPAQuery<Customer>(em)
                .from(QCustomer.customer)
                .where(QCustomer.customer.accountCustomer.username.eq(username))
                .fetchOne();
    }
}
