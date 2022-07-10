package com.ptithcm.clothing_store.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DiscountDto {
    private Long id;
    private Integer percent;
    private Long version;
}
