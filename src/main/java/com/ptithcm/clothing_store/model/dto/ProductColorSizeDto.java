package com.ptithcm.clothing_store.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptithcm.clothing_store.model.entity.ProductColor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductColorSizeDto {
    private Long id;
    private SizeDto size;
    private Integer quantity;
    private ColorProductDto color;
}
