package com.ptithcm.clothing_store;

import com.ptithcm.clothing_store.repository.BillRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootTest
public class BillTest {

    @Autowired
    private BillRepository billTest;

    @Test
    void getReportProceeds(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = LocalDate.parse("2021-01-01",format);
        LocalDate toDate = LocalDate.parse("2022-12-31",format);
        for (Object[] objects:billTest.getReportProceeds(fromDate,toDate)){
            System.out.println(objects[0]+" "+objects[1]+" "+objects[2]);
        }
        //billTest.getReportProceeds(fromDate,toDate);
    }
}
