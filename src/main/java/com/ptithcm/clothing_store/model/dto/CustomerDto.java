package com.ptithcm.clothing_store.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptithcm.clothing_store.model.Enum.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto extends PersonDto {
    @JsonProperty("account")
    AccountDto accountDto;
    private Long version;
}
