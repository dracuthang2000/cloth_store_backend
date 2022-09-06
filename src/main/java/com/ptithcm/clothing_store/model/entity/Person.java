package com.ptithcm.clothing_store.model.entity;

import com.ptithcm.clothing_store.model.Enum.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "PERSON")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person extends IdAndVersion {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "id_card")
    private String idCard;
    @Column(name = "gender")
    @Enumerated(EnumType.ORDINAL)
    private EnumGender gender;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "mail")
    private String mail;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "address")
    private String address;
}
