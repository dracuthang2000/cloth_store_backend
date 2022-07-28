package com.ptithcm.clothing_store.model.dto.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptithcm.clothing_store.model.Enum.EnumState;
import com.ptithcm.clothing_store.model.dto.ReceiverDto;
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
public class BillUpdateDto {
    private Long id;
    private LocalDate date;
    @JsonProperty("is_payment")
    private Boolean isPayment;
    private String note;
    private Long total;
    @JsonProperty("id_customer")
    private Long idCustomer;
    private ReceiverDto receiver;
    private EnumState state;
    @JsonProperty("bill_product_details")
    private List<BillProductDetailUpdateDto> billProductDetails;
    private Long version;
}
