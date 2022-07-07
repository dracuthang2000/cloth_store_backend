package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COLOR_PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductColor extends IdAndVersion {
    @ManyToOne
    @JoinColumn(name = "id_color", referencedColumnName = "id")
    private Color color;
    @ManyToOne
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;
    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "productColor")
    private List<ProductColorSize> productColorSize;

}
