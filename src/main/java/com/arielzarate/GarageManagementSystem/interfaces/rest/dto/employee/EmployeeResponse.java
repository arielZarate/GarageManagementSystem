package com.arielzarate.GarageManagementSystem.interfaces.rest.dto.employee;

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
public class EmployeeResponse {

    private Long id;
    private String legajo;
    private String firstName;
    private String lastName;
    private String dni;
    private String birthDate;
    private String cuit;
    private String email;
    private String phone;
    private String role;
    private Boolean active;
    private String joinDate;

    // Address fields
    private String province;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
    private String postalCode;
    private String country;
}
