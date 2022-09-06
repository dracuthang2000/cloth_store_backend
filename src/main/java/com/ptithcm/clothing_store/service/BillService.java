package com.ptithcm.clothing_store.service;

import com.ptithcm.clothing_store.model.Enum.EnumState;
import com.ptithcm.clothing_store.model.dto.bill.ReportProceeds;
import com.ptithcm.clothing_store.model.entity.Bill;
import com.ptithcm.clothing_store.model.entity.Orders;

import java.time.LocalDate;
import java.util.List;

public interface BillService {
    List<Bill> findAll();
    Bill findById(Long id);
    String save(Bill bill);
    List<Bill> findByCustomerId(Long id);
    List<Bill> findByOrdersState(EnumState state);
    List<ReportProceeds> getReportProceeds(LocalDate fromDate, LocalDate toDate);
    List<Bill> findByOrdersIdShipper(Long id);
    List<Bill> findByOrdersIdShipperAndState(Long id,EnumState state);
}
