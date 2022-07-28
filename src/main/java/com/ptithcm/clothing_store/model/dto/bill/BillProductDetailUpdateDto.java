package com.ptithcm.clothing_store.model.dto.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BillProductDetailUpdateDto {
    private Long id;
    private Integer quantity;
    @JsonProperty("unit_price")
    private Long unitPrice;
    @JsonProperty("id_product_color_size")
    private Long idProductColorSizeDto;
//    @JsonProperty("refund_exchange")
//    List<RefundChangeDto> refundChanges;
    private Long version;
}
