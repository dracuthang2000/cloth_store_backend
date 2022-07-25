package com.ptithcm.clothing_store.mapper;

import com.ptithcm.clothing_store.model.dto.PersonDto;
import com.ptithcm.clothing_store.model.dto.PersonUpdateDto;
import com.ptithcm.clothing_store.model.entity.Customer;
import com.ptithcm.clothing_store.model.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class ApplicationCustomerMapper {
    public ApplicationCustomerMapper(){

    }

    public Customer personDtoToCustomer(PersonDto personDto) {
        Customer entity = new Customer();
        Person person = new Person();
        person.setId(personDto.getId());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setIdCard(personDto.getIdCard());
        person.setBirthDate(personDto.getBirthDate());
        person.setMail(personDto.getMail());
        person.setGender(personDto.getGender());
        person.setPhoneNumber(personDto.getPhoneNumber());
        person.setAddress(personDto.getAddress());
        person.setVersion(personDto.getVersion());
        entity.setPerson(person);
        entity.setAccountCustomer(new ApplicationUserMapper().accountDtoToAccount(personDto.getAccountDto()));
        return entity;
    }

    public Customer personUpdateDtoToCustomer(PersonUpdateDto dto) {
        Customer entity = new Customer();
        Person person = new Person();
        person.setId(dto.getId());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setIdCard(dto.getIdCard());
        person.setBirthDate(dto.getBirthDate());
        person.setMail(dto.getMail());
        person.setGender(dto.getGender());
        person.setPhoneNumber(dto.getPhoneNumber());
        person.setAddress(dto.getAddress());
        person.setVersion(dto.getVersion());
        entity.setPerson(person);
        return entity;
    }

}
