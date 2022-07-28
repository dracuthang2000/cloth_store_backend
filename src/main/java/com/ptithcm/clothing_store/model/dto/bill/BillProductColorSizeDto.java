package com.ptithcm.clothing_store.model.dto.bill;

import com.ptithcm.clothing_store.model.dto.ColorProductDto;
import com.ptithcm.clothing_store.model.dto.SizeDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BillProductColorSizeDto {
    private Long id;
    private SizeDto size;
    private Integer quantity;
    private BillColorProductDto color;
}
