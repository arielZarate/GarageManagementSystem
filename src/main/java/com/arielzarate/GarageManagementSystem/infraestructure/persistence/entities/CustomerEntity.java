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
public class CustomerEntity extends BaseEntity {

    @Column(unique = true)
    private String customerCode;

    private String firstName;

    private String lastName;

    private String phone;

    private String email;

    @Column(unique = true)
    private String cuit;

    @Column(unique = true)
    private String dni;

    @Embedded
    private AddressEmbeddable address;

    private Boolean active = true;

}
