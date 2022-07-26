package com.ptithcm.clothing_store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class StaffDto {
    private Long id;
    private PersonDto person;
    private List<OrderDto> orders;
}
