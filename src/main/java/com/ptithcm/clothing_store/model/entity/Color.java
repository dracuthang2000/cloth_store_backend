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
@Table(name = "COLOR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Color extends IdAndVersion {
    @Column(name = "color")
    private String color;

    @OneToMany(mappedBy = "color")
    private List<ProductColor> productColor;
}
