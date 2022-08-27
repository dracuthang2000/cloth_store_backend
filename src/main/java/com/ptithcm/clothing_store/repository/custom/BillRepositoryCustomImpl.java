package com.ptithcm.clothing_store.repository.custom;

import com.ptithcm.clothing_store.model.Enum.EnumState;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillRepositoryCustomImpl implements BillRepositoryCustom {

    @Autowired
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Object[]> getReportProceeds(LocalDate fromDate, LocalDate toDate) {
        List<Object[]> lstObject = new ArrayList<>();
        Query query = em.createQuery("SELECT YEAR(O.startDate), MONTH(O.startDate),SUM(B.total) FROM Bill B " +
                "INNER JOIN Orders O ON B.id = O.bill.id " +
                "WHERE YEAR(O.startDate) <= YEAR(:toDate) AND MONTH(O.startDate) <= MONTH(:toDate) " +
                "AND YEAR(O.startDate) >= YEAR(:fromDate) AND MONTH(O.startDate) >= MONTH(:fromDate) " +
                "AND O.state = :state " +
                "GROUP BY MONTH(O.startDate),YEAR(O.startDate)");
        lstObject = query
                .setParameter("toDate",toDate)
                .setParameter("fromDate",fromDate)
                .setParameter("state", EnumState.FIN)
                .getResultList();
        return lstObject;
    }
}
