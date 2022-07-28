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
@Getter@Setter
public class BillDto {
    private Long id;
    private LocalDate date;
    @JsonProperty("is_payment")
    private Boolean isPayment;
    private String note;
    private Long total;
    private EnumState state;
    @JsonProperty("id_customer")
    private Long idCustomer;
    private ReceiverDto receiver;
    @JsonProperty("bill_product_details")
    private List<BillProductDetailDto> billProductDetails;
    private Long version;
}
