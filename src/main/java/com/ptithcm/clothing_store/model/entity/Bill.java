package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BILL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bill extends IdAndVersion {
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "is_payment")
    private Boolean isPayment;
    @Column(name = "note")
    private String note;
    @Column(name = "total")
    private Long total;
    @ManyToOne
    @JoinColumn(name = "id_customer",referencedColumnName = "id")
    private Customer customer;
    @OneToMany(mappedBy = "bill")
    private Set<BillProductDetail> billProductDetails = new HashSet<>();
    @OneToMany(mappedBy = "bill")
    private Set<Order> orders = new HashSet();
}
