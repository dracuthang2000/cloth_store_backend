package com.ptithcm.clothing_store.model.entity;

import com.ptithcm.clothing_store.model.Enum.EnumState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "REFUND_CHANGE")
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class RefundChange extends IdAndVersion {
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "reason")
    private String reason;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private EnumState state;
    @Column(name = "image")
    private String img;
    @ManyToOne
    @JoinColumn(name = "id_bill_product",referencedColumnName = "id")
    private BillProductDetail billProductDetail;
}
