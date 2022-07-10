package com.ptithcm.clothing_store.util;

import com.ptithcm.clothing_store.model.dto.ProductDiscountDto;

import java.time.LocalDate;
import java.util.Objects;

public class DiscountUtil {
    public static Boolean discountNow(ProductDiscountDto dto){
        if(Objects.isNull(dto.getEndDate())&&dto.getStartDate().isBefore(LocalDate.now())){
            return true;
        }else if (dto.getStartDate().isAfter(LocalDate.now())){
            return false;
        }else if(dto.getEndDate().isBefore(LocalDate.now())){
            return false;
        }
        return true;
    }
}
