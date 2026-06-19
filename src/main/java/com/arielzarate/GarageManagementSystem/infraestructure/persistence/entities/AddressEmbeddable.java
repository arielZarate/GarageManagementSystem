package com.arielzarate.GarageManagementSystem.infraestructure.persistence.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class AddressEmbeddable {

    private String province;

    private String city;

    private String neighborhood;

    private String street;

    private String number;

    private String postalCode;

    private String country;

    private String reference;

}
