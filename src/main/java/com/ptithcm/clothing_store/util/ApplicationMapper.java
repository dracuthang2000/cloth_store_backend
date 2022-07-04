package com.ptithcm.clothing_store.util;

import com.google.gson.Gson;
import com.ptithcm.clothing_store.model.dto.*;
import com.ptithcm.clothing_store.model.entity.Account;
import com.ptithcm.clothing_store.model.entity.Customer;
import com.ptithcm.clothing_store.model.entity.Person;
import com.ptithcm.clothing_store.model.entity.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ApplicationMapper {
    public ApplicationMapper() {
    }

    public UserDto mapperUser(Customer entity) {
        UserDto dto = new UserDto();
        dto.setUsername(entity.getAccountCustomer().getUsername());
        dto.setRoleDto(this.roleToRoleDto(entity.getAccountCustomer().getRole()));
        dto.setFirstName(entity.getPerson().getFirstName());
        dto.setLastName(entity.getPerson().getLastName());
        dto.setMail(entity.getPerson().getMail());
        dto.setAddress(entity.getPerson().getAddress());
        dto.setBirthDate(entity.getPerson().getBirthDate());
        dto.setId(entity.getPerson().getId());
        dto.setIdCard(entity.getPerson().getIdCard());
        dto.setPhoneNumber(entity.getPerson().getPhoneNumber());
        dto.setVersion(entity.getPerson().getVersion());
        dto.setGender(entity.getPerson().getGender());
        return dto;
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
        entity.setAccountCustomer(this.accountDtoToAccount(personDto.getAccountDto()));
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

    public RoleDto roleToRoleDto(Role entity) {
        RoleDto dto = new RoleDto();
        dto.setId(entity.getId());
        dto.setRole(entity.getRole());
        dto.setVersion(entity.getVersion());
        return dto;
    }

    public Role roleDtoToRole(RoleDto roleDto) {
        Role entity = new Role();
        entity.setId(roleDto.getId());
        entity.setRole(roleDto.getRole());
        entity.setVersion(roleDto.getVersion());
        return entity;
    }

    public User jsonToUserDetail(String data) {
        Gson gson = new Gson();
        UserDto dto = gson.fromJson(data, UserDto.class);
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(dto.getRoleDto().getRole().name()));
        User user = new User(dto.getUsername(),"",authorities);
        return user;
    }

    public UserDto jsonToUserDto(String data) {
        Gson gson = new Gson();
        UserDto dto = gson.fromJson(data, UserDto.class);
        return dto;
    }

    public Account accountDtoToAccount(AccountDto dto) {
        Account entity = new Account();
        entity.setUsername(dto.getUsername());
        entity.setPassword(dto.getPassword());
        entity.setRole(this.roleDtoToRole(dto.getRoleDto()));
        entity.setIsActive(dto.getIsActive());
        entity.setVersion(dto.getVersion());
        return entity;
    }
}
