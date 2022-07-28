package com.ptithcm.clothing_store.mapper;

import com.ptithcm.clothing_store.model.dto.CustomerDto;
import com.ptithcm.clothing_store.model.dto.CustomerUpdateDto;
import com.ptithcm.clothing_store.model.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCustomerMapper {
    public ApplicationCustomerMapper(){

    }

    public Customer customerDtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setId(customerDto.getId());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setIdCard(customerDto.getIdCard());
        customer.setBirthDate(customerDto.getBirthDate());
        customer.setMail(customerDto.getMail());
        customer.setGender(customerDto.getGender());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        customer.setVersion(customerDto.getVersion());
        customer.setAccountCustomer(new ApplicationUserMapper().accountDtoToAccount(customerDto.getAccountDto()));
        return customer;
    }


    public CustomerDto customerToCustomerDto(Customer customer){
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setAddress(customer.getAddress());
        dto.setBirthDate(customer.getBirthDate());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setIdCard(customer.getIdCard());
        dto.setMail(customer.getMail());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setGender(customer.getGender());
        dto.setVersion(customer.getVersion());
        return dto;
    }

    public Customer customerUpdateToCustomer(CustomerUpdateDto dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setFirstName(dto.getFirstName());
        customer.setLastName(dto.getLastName());
        customer.setIdCard(dto.getIdCard());
        customer.setBirthDate(dto.getBirthDate());
        customer.setMail(dto.getMail());
        customer.setGender(dto.getGender());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setAddress(dto.getAddress());
        customer.setVersion(dto.getVersion());
        return customer;
    }

}
