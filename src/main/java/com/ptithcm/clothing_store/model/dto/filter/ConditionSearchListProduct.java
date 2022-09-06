package com.ptithcm.clothing_store.model.dto.filter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ConditionSearchListProduct {
    @JsonProperty("is_new")
    Boolean isNew;
    @JsonProperty("key_word")
    String keyWord;
}
