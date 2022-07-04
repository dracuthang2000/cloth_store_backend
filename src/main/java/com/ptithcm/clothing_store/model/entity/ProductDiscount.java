package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PRODUCT_DISCOUNT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDiscount {
    @EmbeddedId
    private ProductDiscountKey id;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @ManyToOne
    @JoinColumn(name = "id_product",referencedColumnName = "id")
    @MapsId("productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "id_discount",referencedColumnName = "id")
    @MapsId("discountId")
    private Discount discount;
    @Version
    private Long version;
}
