package com.ptithcm.clothing_store.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BrandDto {
    private Long id;
    private String brand;
    private String image;
    @JsonProperty("tag_brand")
    private String tag;
    private Long version;
    @JsonProperty("image_byte")
    private String imageByte;
}
