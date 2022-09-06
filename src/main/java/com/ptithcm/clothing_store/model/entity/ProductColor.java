package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "COLOR_PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedEntityGraph(
        name = "graph-product-color",
        attributeNodes = {
                @NamedAttributeNode(value = "productColorSize",subgraph = "sub-size")
                ,@NamedAttributeNode("color")
        },
        subgraphs = {
                @NamedSubgraph(name = "sub-size"
                        ,attributeNodes = {@NamedAttributeNode("size")})
        }
        )
public class ProductColor extends IdAndVersion {
    @ManyToOne
    @JoinColumn(name = "id_color", referencedColumnName = "id")
    private Color color;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "id_product", referencedColumnName = "id")
    private Product product;
    @Column(name = "image")
    private String image;
    @OneToMany(mappedBy = "productColor",cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    private Set<ProductColorSize> productColorSize = new HashSet<>();

    public void addProductColorSize(Set<ProductColorSize> colors){
        if(!Objects.isNull(colors)){
            this.setProductColorSize(colors);
            colors.stream().forEach(e->
                    e.setProductColor(this));
        }
    }
}
