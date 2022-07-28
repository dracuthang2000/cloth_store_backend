package com.ptithcm.clothing_store.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptithcm.clothing_store.model.Enum.EnumState;
import com.ptithcm.clothing_store.model.dto.bill.BillProductDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class RefundChangeDto {
    private Long id;
    private Integer quantity;
    private LocalDate date;
    private String reason;
    private EnumState state;
    private String img;
    @JsonProperty("bill_product_detail")
    private BillProductDetailDto billProductDetailDto;
}
