package com.ptithcm.clothing_store.model.dto.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptithcm.clothing_store.model.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BillProductDto {
    private Long id;
    @JsonProperty("product_name")
    private String productName;
    private String description;
    private String img;
    private BrandDto brand;
    private MaterialDto material;
    private LabelDto label;
    private GenderDto gender;
}
