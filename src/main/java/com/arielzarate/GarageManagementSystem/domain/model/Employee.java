package com.arielzarate.GarageManagementSystem.domain.model;

import java.time.LocalDate;

import com.arielzarate.GarageManagementSystem.domain.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Long id;
    private String legajo;
    private String firstName;
    private String lastName;
    private String dni;
    private LocalDate birthDate;
    private String CUIT;
    private String email;
    private String password;
    private String phone;
    private Address address;
    private Role role;
    private Boolean active;
    private LocalDate joinDate;
}
