package com.ptithcm.clothing_store.mapper;

import com.google.gson.Gson;
import com.ptithcm.clothing_store.model.dto.AccountDto;
import com.ptithcm.clothing_store.model.dto.RoleDto;
import com.ptithcm.clothing_store.model.dto.UserDto;
import com.ptithcm.clothing_store.model.entity.Account;
import com.ptithcm.clothing_store.model.entity.Customer;
import com.ptithcm.clothing_store.model.entity.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationUserMapper {
    public ApplicationUserMapper(){
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
