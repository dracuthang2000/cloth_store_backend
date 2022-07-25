package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BILL_PRODUCT_DETAIL")
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class BillProductDetail extends IdAndVersion {
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "unit_price")
    private Long unitPrice;
    @ManyToOne
    @JoinColumn(name = "id_bill",referencedColumnName = "id")
    private Bill bill;
    @ManyToOne
    @JoinColumn(name = "id_product_color_size",referencedColumnName = "id")
    private ProductColorSize productColorSize;
    @OneToMany(mappedBy = "billProductDetail")
    private Set<RefundChange> refundChanges;
}
