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
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "MATERIAL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Material extends IdAndVersion {
    @Column(name = "material_name")
    private String materialName;
    @Column(name = "tag")
    private String tag;
    @OneToMany(mappedBy = "material")
    Set<Product> products = new HashSet<>();
}
