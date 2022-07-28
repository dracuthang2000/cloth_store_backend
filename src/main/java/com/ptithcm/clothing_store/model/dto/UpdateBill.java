package com.ptithcm.clothing_store.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptithcm.clothing_store.model.dto.bill.BillDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateBill{
    private Long id;
    private Integer quantity;
    @JsonProperty("unit_price")
    private Long unitPrice;
    private BillDto bill;
    @JsonProperty("id_product_color_sizes")
    private List<Long> idProductColorSizeDto;
    private LocalDate date;
    @JsonProperty("is_payment")
    private Boolean isPayment;
    private String note;
    private Long total;
    private CustomerDto customer;
    private Long version;
}
