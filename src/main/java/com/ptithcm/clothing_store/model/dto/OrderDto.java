package com.ptithcm.clothing_store.model.dto;

import com.ptithcm.clothing_store.model.Enum.EnumState;
import com.ptithcm.clothing_store.model.dto.bill.BillDto;
import com.ptithcm.clothing_store.model.entity.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class OrderDto {
    private Long id;
    private EnumState state;
    private LocalDate startDate;
    private LocalDate endDate;
    private String note;
    private BillDto bill;
    private Customer staff;
}
