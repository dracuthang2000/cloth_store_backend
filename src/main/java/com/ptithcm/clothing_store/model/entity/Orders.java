package com.ptithcm.clothing_store.model.entity;

import com.ptithcm.clothing_store.model.Enum.EnumState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ORDERS")
@AllArgsConstructor
@NoArgsConstructor
@Getter@Setter
public class Orders extends IdAndVersion{
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private EnumState state;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "note")
    private String note;
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "id_bill", referencedColumnName = "id")
    private Bill bill;
    @ManyToOne
    @JoinColumn(name = "id_staff",referencedColumnName = "id")
    private Staff staff;
    @ManyToOne
    @JoinColumn(name = "id_driver",  referencedColumnName = "id")
    private Staff driver;
}
