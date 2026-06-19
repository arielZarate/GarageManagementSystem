package com.arielzarate.GarageManagementSystem.interfaces.rest.dto.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {
    private String province;
    private String city;
    private String neighborhood;
    private String street;
    private String number;
    private String postalCode;
    private String country;
}
