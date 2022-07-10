package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCT_COLOR_SIZE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductColorSize {
    @Id
    private Long id;
    @Column(name = "quantity")
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "id_product_color",referencedColumnName = "id")
    private ProductColor productColor;
    @ManyToOne
    @JoinColumn(name = "id_size",referencedColumnName = "id")
    private Size size;
}
