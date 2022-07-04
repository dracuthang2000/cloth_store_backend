package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "DISCOUNT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Discount extends IdAndVersion {
    @Column(name = "percent_discount")
    private Integer percent;
    @OneToMany(mappedBy = "discount")
    private List<ProductDiscount> productDiscounts;
}
