package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_SIZE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductSize {
    @EmbeddedId
    private ProductSizeKey id;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "id_product",referencedColumnName = "id")
    @MapsId("productId")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "id_size",referencedColumnName = "id")
    @MapsId("sizeId")
    private Size size;
}
