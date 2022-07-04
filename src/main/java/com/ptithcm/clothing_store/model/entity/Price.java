package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "PRICE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Price extends IdAndVersion {
    @Column(name = "price")
    private Long price;
    @Column(name = "start_date")
    private LocalDate startDate;
    @ManyToOne
    @JoinColumn(name = "id_product",referencedColumnName = "id")
    private Product product;
}
