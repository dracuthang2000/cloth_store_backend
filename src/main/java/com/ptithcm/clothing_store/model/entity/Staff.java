package com.ptithcm.clothing_store.model.entity;

import com.ptithcm.clothing_store.model.Enum.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "STAFF")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedEntityGraph(
        name = "staff_graphic",
        attributeNodes = {@NamedAttributeNode(value = "accountStaff",subgraph = "sub-graphic-account-staff")},
        subgraphs = {@NamedSubgraph(
                name = "sub-graphic-account-staff"
                ,attributeNodes ={@NamedAttributeNode("role")} )}
)
public class Staff extends Person{
    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "username",referencedColumnName = "username")
    private Account accountStaff;
    @OneToMany(mappedBy = "staff")
    private Set<Orders> orders;
    @OneToMany(mappedBy = "driver")
    private Set<Orders> driver;
}
