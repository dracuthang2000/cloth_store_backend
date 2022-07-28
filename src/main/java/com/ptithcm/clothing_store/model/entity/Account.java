package com.ptithcm.clothing_store.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@NamedEntityGraph(
        name = "account_graphic",
        attributeNodes = {@NamedAttributeNode(value = "role")}
)
public class Account{
    @Id
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "is_active")
    private Boolean isActive;
    @Version
    @Column(name = "version")
    private Long version;
    @OneToOne(mappedBy = "accountCustomer")
    private Customer customer;
    @OneToOne(mappedBy = "accountStaff")
    private Staff staff;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
