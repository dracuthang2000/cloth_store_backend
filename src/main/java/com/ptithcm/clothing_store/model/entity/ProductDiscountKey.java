package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.tomcat.jni.Local;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDiscountKey implements Serializable {
    @Column(name = "id_discount")
    private Long discountId;
    @Column(name = "id_product")
    private Long productId;
    @Column(name = "start_date")
    private LocalDate startDate;
}
