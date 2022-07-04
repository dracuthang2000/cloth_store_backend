package com.ptithcm.clothing_store.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountDto {
    private String username;
    private String password;
    @JsonProperty("role")
    private RoleDto roleDto;
    @JsonProperty("is_active")
    private Boolean isActive;
    private Long version;
}
