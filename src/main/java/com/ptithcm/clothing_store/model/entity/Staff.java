package com.ptithcm.clothing_store.model.entity;

import com.ptithcm.clothing_store.model.Enum.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STAFF")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Staff implements Serializable {
    @Id
    private Long id;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "id",referencedColumnName = "id")
    @MapsId("id")
    private Person person;
    @OneToOne(cascade = {CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name = "username",referencedColumnName = "username")
    private Account accountStaff;
    @OneToMany(mappedBy = "staff")
    private Set<Order> orders = new HashSet<>();
    public void setPerson(Person person) {
        this.person = person;
        this.id = person.getId();
    }
}
