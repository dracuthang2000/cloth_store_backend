package com.ptithcm.clothing_store.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptithcm.clothing_store.model.Enum.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PersonUpdateDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("first_name")
    @NotBlank(message = "first name isn't blank")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "last name isn't blank")
    private String lastName;

    @JsonProperty("id_card")
    @NotBlank(message = "id card isn't blank")
    private String idCard;

    @JsonProperty("gender")
    private EnumGender gender;

    @JsonProperty("phone_number")
    @NotBlank(message = "phone number isn't blank")
    private String phoneNumber;

    @JsonProperty("mail")
    @NotBlank(message = "mail isn't blank")
    private String mail;

    @JsonProperty("birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @JsonProperty("address")
    @NotBlank(message = "first name isn't blank")
    private String address;

    @JsonProperty("role")
    private RoleDto role;

    private String username;

    private Long version;
}
