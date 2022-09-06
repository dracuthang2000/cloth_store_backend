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
public class Customer extends Person{
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "username",referencedColumnName = "username")
    private Account accountCustomer;
    @OneToMany(mappedBy = "customer")
    private Set<Bill> bills = new HashSet<>();
}
