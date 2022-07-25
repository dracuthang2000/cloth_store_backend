package com.ptithcm.clothing_store.model.entity;

import com.ptithcm.clothing_store.model.Enum.EnumState;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ORDER")
public class Order extends IdAndVersion{
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private EnumState state;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "note")
    private String note;
    @ManyToOne
    @JoinColumn(name = "id_bill",referencedColumnName = "id")
    private Bill bill;
    @ManyToOne
    @JoinColumn(name = "id_staff",referencedColumnName = "id")
    private Staff staff;
}
