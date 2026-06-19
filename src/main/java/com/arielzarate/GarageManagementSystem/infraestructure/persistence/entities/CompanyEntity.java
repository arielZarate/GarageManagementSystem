package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "company")
public class CompanyEntity extends BaseEntity {

    private String businessName;

    private String legalName;

    private String logo;

    private String phone;

    private String email;

    private String horario;

    private String cuit;

    @Embedded
    private AddressEmbeddable address;

}
