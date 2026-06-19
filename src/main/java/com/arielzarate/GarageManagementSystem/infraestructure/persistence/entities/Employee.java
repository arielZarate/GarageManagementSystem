package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import com.arielzarate.GarageManagementSystem.infraestructure.persistence.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee extends BaseEntity {

    private String legajo;

    private LocalDate joinDate;

    private String CUIT;

    private String firstName;

    private String lastName;

    private String dni ;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(unique = true)
    private String email;

    private String password;

    private String phone;

    @Embedded
    private AddressEmbeddable address;

    private Boolean active = true;


}

