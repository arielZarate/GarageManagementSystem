package com.arielzarate.GarageManagementSystem.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Long id;
    private String customerCode;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String cuit;
    private String dni;
    private Address address;
    private Boolean active;
}
