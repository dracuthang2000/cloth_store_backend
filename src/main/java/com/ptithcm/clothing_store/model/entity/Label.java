package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "LABEL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Label extends IdAndVersion {
    @Column(name = "label_name")
    private String labelName;
    @Column(name = "tag")
    private String tag;
    @OneToMany(mappedBy = "label")
    private Set<Product> product = new HashSet<>();
}
