package com.ptithcm.clothing_store.model.dto.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class FilterProduct {
    @JsonProperty("tag_material")
    private String tagMaterial;
    @JsonProperty("tag_label")
    private String tagLabel;
    @JsonProperty("tag_brand")
    private String tagBrand;
}
