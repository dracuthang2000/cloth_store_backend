package com.ptithcm.clothing_store.model.entity;

import com.ptithcm.clothing_store.model.Enum.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ROLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role extends IdAndVersion {
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EnumRole role;
    @OneToMany(mappedBy = "role")
    private List<Account> accounts;
}
