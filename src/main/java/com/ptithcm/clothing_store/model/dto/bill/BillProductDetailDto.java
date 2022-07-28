package com.ptithcm.clothing_store.model.dto.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ptithcm.clothing_store.model.dto.ProductColorSizeDto;
import com.ptithcm.clothing_store.model.dto.RefundChangeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class BillProductDetailDto {
    private Long id;
    private Integer quantity;
    @JsonProperty("unit_price")
    private Long unitPrice;
    @JsonProperty("product_color_size")
    private BillProductColorSizeDto productColorSizeDto;
    @JsonProperty("refund_exchange")
    List<RefundChangeDto> refundChanges;
    private Long version;
}
