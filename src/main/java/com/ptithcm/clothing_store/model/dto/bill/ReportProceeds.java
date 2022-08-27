package com.ptithcm.clothing_store.model.dto.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class ReportProceeds {
    @JsonProperty("year_month")
    private String yearMonth;
    private Long total;
}
