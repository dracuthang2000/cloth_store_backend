package com.ptithcm.clothing_store.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ColorProductDto {
    private Long id;
    private ColorDto color;
    @JsonProperty("color_size")
    private List<ProductColorSizeDto> productColorSizesDto;
    @JsonProperty("image_name")
    private String img;
    private Long version;
    private ProductDto product;
}
