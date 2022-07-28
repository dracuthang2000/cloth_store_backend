package com.ptithcm.clothing_store.model.dto.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptithcm.clothing_store.model.dto.ColorDto;
import com.ptithcm.clothing_store.model.dto.ProductColorSizeDto;
import com.ptithcm.clothing_store.model.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BillColorProductDto {
    private Long id;
    private ColorDto color;
    @JsonProperty("img")
    private String img;
    private Long version;
    private BillProductDto product;
}
