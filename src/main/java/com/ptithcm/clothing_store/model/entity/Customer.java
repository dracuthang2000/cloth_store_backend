package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CUSTOMER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer implements Serializable {
    @Id
    private Long id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id",referencedColumnName = "id")
    @MapsId("id")
    private Person person;
    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name = "username",referencedColumnName = "username")
    private Account accountCustomer;

    @OneToMany(mappedBy = "customer")
    private Set<Bill> bills = new HashSet<>();

    public void setPerson(Person person) {
        this.person = person;
        this.id = person.getId();
    }
}
