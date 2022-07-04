package com.ptithcm.clothing_store.model.entity;

import com.ptithcm.clothing_store.model.Enum.EnumSize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "SIZE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Size extends IdAndVersion {

    @Column(name = "size_name")
    @Enumerated(EnumType.STRING)
    private EnumSize size;
    @OneToMany(mappedBy = "size")
    private List<ProductSize> productSizes;
}
