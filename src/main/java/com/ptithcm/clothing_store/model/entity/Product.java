package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends IdAndVersion {
    @Column(name = "product_name")
    private String productName;
    @Column(name = "description")
    private String description;
    @Column(name = "image")
    private String image;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "product")
    private List<ProductColor> productColors;
    @ManyToOne
    @JoinColumn(name = "id_label",referencedColumnName = "id")
    private Label label;
    @ManyToOne
    @JoinColumn(name = "id_brand", referencedColumnName = "id")
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "id_stuff",referencedColumnName = "id")
    private Stuff stuff;
    @ManyToOne
    @JoinColumn(name = "id_gender",referencedColumnName = "id")
    private Gender gender;

    @OneToMany(mappedBy = "product")
    private List<ProductSize> productSize;
    @OneToMany(mappedBy = "product")
    private List<Price> prices;
    @OneToMany(mappedBy = "product")
    private List<ProductDiscount> productDiscounts;
}
