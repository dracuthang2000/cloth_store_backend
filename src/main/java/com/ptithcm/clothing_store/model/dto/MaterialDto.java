package com.ptithcm.clothing_store.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class MaterialDto {
    private Long id;
    @JsonProperty("material_name")
    private String materialName;
    @JsonProperty("tag_material")
    private String tag;
    private Long version;
}
