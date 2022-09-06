package com.ptithcm.clothing_store.model.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptithcm.clothing_store.model.Enum.EnumState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateStateProcessing {
    private Long id;
    @JsonProperty("id_shipper")
    private Long id_shipper;
    private EnumState state;
}
