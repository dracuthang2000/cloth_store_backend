package com.ptithcm.clothing_store.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private Long id;
    @JsonProperty("product_name")
    private String productName;
    private String description;
    private String img;
    private BrandDto brand;
    private List<ColorProductDto> color;
    private List<PriceDto> price;
    private StuffDto stuff;
    private LabelDto label;
    private GenderDto gender;
    @JsonProperty("is_active")
    private Boolean isActive;
    private Long version;
}
