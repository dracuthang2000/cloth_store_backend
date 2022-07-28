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
    @JsonProperty("price_log")
    private List<PriceDto> priceLog;
    @JsonProperty("discount_log")
    private List<ProductDiscountDto> discounts;
    private DiscountDto discount;
    private MaterialDto material;
    private LabelDto label;
    private GenderDto gender;
    private Long price;
    private String tag;
    @JsonProperty("is_active")
    private Boolean isActive;
    private Long version;
    @JsonProperty("is_new")
    private Boolean isNew;
}
