package com.arielzarate.GarageManagementSystem.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Company {

    private Long id;
    private String businessName;
    private String legalName;
    private String logo;
    private String phone;
    private String email;
    private String horario;
    private String cuit;
    private Address address;
}
