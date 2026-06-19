package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    @Column(unique = true)
    private String legajo;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    private String cuit;

    private String dni;

    @Embedded
    private AddressEmbeddable address;

    private Boolean active = true;

}
