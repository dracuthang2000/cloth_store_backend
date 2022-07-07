package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@NamedEntityGraph(
        name = "graph-product",
        attributeNodes = {
                @NamedAttributeNode("prices"),
                @NamedAttributeNode("brand"),
                @NamedAttributeNode("label"),
                @NamedAttributeNode("stuff"),
                @NamedAttributeNode("gender"),
                @NamedAttributeNode("productDiscounts"),
                @NamedAttributeNode(value = "productColors",subgraph = "subgraph.product-color-size")
        },
        subgraphs ={
                @NamedSubgraph(name = "subgraph.product-color-size",
                        attributeNodes = {
                        @NamedAttributeNode("color")
                                ,@NamedAttributeNode(value = "productColorSize",subgraph = "sub-size")
                }),
                @NamedSubgraph(name = "sub-size"
                        ,attributeNodes = {@NamedAttributeNode("size")})
        }
)
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
    private Set<ProductColor> productColors;
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
    private Set<Price> prices;
    @OneToMany(mappedBy = "product")
    private Set<ProductDiscount> productDiscounts;
}
