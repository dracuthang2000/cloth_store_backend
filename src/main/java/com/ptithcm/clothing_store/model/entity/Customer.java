package com.ptithcm.clothing_store.model.entity;

import com.ptithcm.clothing_store.model.Enum.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedEntityGraph(
        name = "customer_graphic",
        attributeNodes = {@NamedAttributeNode(value = "accountCustomer",subgraph = "sub-graphic-account-customer")},
        subgraphs = {@NamedSubgraph(
                name = "sub-graphic-account-customer"
                ,attributeNodes ={@NamedAttributeNode("role")} )}
)
public class Customer extends IdAndVersion{
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
    @OneToOne
    @JoinColumn(name = "username",referencedColumnName = "username")
    private Account accountCustomer;
    @OneToMany(mappedBy = "customer")
    private Set<Bill> bills = new HashSet<>();
}
