package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BRAND")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brand extends IdAndVersion{
    @Column(name = "brand_name")
    private String brandName;
    private String image;
    @Column(name = "tag")
    private String tag;
    @OneToMany(mappedBy = "brand")
    private Set<Product> products = new HashSet<>();
}
