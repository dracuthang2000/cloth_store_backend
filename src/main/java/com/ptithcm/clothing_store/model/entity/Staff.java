package com.ptithcm.clothing_store.model.entity;

import com.ptithcm.clothing_store.model.Enum.EnumGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "STAFF")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Staff{
    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "id",referencedColumnName = "id")
    @MapsId
    private Person person;
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private Account accountStaff;
}
