package com.ptithcm.clothing_store.repository.custom;

import java.time.LocalDate;
import java.util.List;

public interface BillRepositoryCustom {
    List<Object[]> getReportProceeds(LocalDate fromDate,LocalDate toDate);
}
