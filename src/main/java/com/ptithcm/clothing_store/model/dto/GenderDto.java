package com.ptithcm.clothing_store.model.dto;

import com.ptithcm.clothing_store.model.Enum.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenderDto {
    private Long id;
    private EnumGender gender;
    private Long version;
}
