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
public class PersonDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("id_card")
    private String idCard;

    @JsonProperty("gender")
    private EnumGender gender;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("mail")
    private String mail;

    @JsonProperty("birth_date")
    private LocalDate birthDate;

    @JsonProperty("address")
    private String address;

    private Long version;
}
