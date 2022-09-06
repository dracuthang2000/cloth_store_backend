package com.ptithcm.clothing_store.service.serviceImpl;

import com.ptithcm.clothing_store.model.CSConstants.CSConstant;
import com.ptithcm.clothing_store.model.Enum.EnumState;
import com.ptithcm.clothing_store.model.dto.bill.ReportProceeds;
import com.ptithcm.clothing_store.model.entity.Bill;
import com.ptithcm.clothing_store.model.exception.ResourceNotFoundException;
import com.ptithcm.clothing_store.repository.BillRepository;
import com.ptithcm.clothing_store.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BillServiceImpl implements BillService {
    public static final String SUCCESS = "SUCCESS";
    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill findById(Long id) {
        return billRepository.findById(id).orElseThrow(()->{
            return new ResourceNotFoundException("Bill can't found");
        });
    }

    @Override
    public String save(Bill bill) {
        billRepository.save(bill);
        return CSConstant.SUCCESS;
    }

    @Override
    public List<Bill> findByCustomerId(Long id) {
        List<Bill> bills = billRepository.findByCustomerId(id);
        if(bills.size() == 0){
            throw new ResourceNotFoundException("Can't found bill");
        }
        return bills;
    }

    @Override
    public List<Bill> findByOrdersState(EnumState state) {
        List<Bill> bills = billRepository.findAllByOrdersState(state);
        if(bills.size() == 0){
            throw new ResourceNotFoundException("Can't found bill");
        }
        return bills;
    }

    @Override
    public List<ReportProceeds> getReportProceeds(LocalDate fromDate, LocalDate toDate) {
        List<Object[]> lstObject = billRepository.getReportProceeds(fromDate,toDate);
        List<ReportProceeds> result = new ArrayList<>();
        if(!Objects.isNull(lstObject)&&lstObject.size()!=0){
            lstObject.stream().forEach((data)->{
                ReportProceeds reportProceeds = new ReportProceeds();
                reportProceeds.setYearMonth(data[1]+"-"+data[0]);
                reportProceeds.setTotal((Long) data[2]);
                result.add(reportProceeds);
            });
        }
        return result;
    }

    @Override
    public List<Bill> findByOrdersIdShipper(Long id) {
        return billRepository.findAllByOrdersDriverId(id);
    }

    @Override
    public List<Bill> findByOrdersIdShipperAndState(Long id, EnumState state) {
        return billRepository.findAllByOrdersDriverIdAndOrdersState(id,state);
    }
}
