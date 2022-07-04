package com.ptithcm.clothing_store.model.entity;

import com.ptithcm.clothing_store.model.Enum.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "GENDER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Gender extends IdAndVersion {
    @Enumerated(EnumType.STRING)
    @Column(name = "gender_name")
    private EnumGender genderName;
    @OneToMany(mappedBy = "gender")
    private List<Product> products;
}
