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
@Table(name = "STUFF")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Stuff extends IdAndVersion {
    @Column(name = "stuff_name")
    private String stuffName;
    @OneToMany(mappedBy = "stuff")
    Set<Product> products = new HashSet<>();
}
