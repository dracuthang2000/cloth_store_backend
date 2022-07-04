package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.entity.Customer;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.model.exception.UsernameNotFoundException;
import com.ptithcm.clothing_store.repository.CustomerRepository;
import com.ptithcm.clothing_store.service.CustomerService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private static final String SUCCESS ="SUCCESS";

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public Customer findCustomerByUsername(String username) {
        Customer customer = null;
        customer = customerRepository.findByAccountCustomer_Username(username);
        if(Objects.isNull(customer)){
            throw new UsernameNotFoundException("Username can't found");
        }
        return customer;
    }


    @Override
    public String save(Customer customer) {
        if(customer.getPerson().getId()==0) {
            customer.getAccountCustomer()
                    .setPassword(bCryptPasswordEncoder.encode(customer.getAccountCustomer().getPassword()));
        }
        customerRepository.save(customer);
        return SUCCESS;
    }

    @Override
    public Customer findCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(()->{
            return new ResourceNotFoundException("The customer can't found");
        });
        return customer;
    }
}
