package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "LABEL")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Label extends IdAndVersion {
    @Column(name = "label_name")
    private String labelName;
    @OneToMany(mappedBy = "label")
    private List<Product> product;
}
