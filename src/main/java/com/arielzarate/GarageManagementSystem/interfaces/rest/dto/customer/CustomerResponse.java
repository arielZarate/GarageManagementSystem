package com.arielzarate.GarageManagementSystem.interfaces.rest.dto.customer;

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
public class CustomerResponse {

    private Long id;
    private String customerCode;
    private String firstName;
    private String lastName;
    private String dni;
    private String cuit;
    private String email;
    private String phone;
    private Boolean active;

    // Address fields
    private String province;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
    private String postalCode;
    private String country;
}
