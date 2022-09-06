package com.ptithcm.clothing_store.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class Image {
    @JsonProperty("image_name")
    private String imageName;
    @JsonProperty("image_byte")
    private String imageByte;
}
