package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "COLOR_PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductColor {
    @EmbeddedId
    private ProductColorKey id;
    @ManyToOne
    @JoinColumn(name = "id_color", referencedColumnName = "id")
    @MapsId("colorId")
    private Color color;
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    @MapsId("productId")
    private Product product;
    @Column(name = "image")
    private String image;
    @Version
    private Long version;
}
