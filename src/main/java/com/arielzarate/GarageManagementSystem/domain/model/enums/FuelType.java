package com.arielzarate.GarageManagementSystem.domain.model.enums;

public enum FuelType {

    GASOLINE("Nafta"),
    DIESEL("Diesel"),
    ELECTRIC("Eléctrico"),
    HYBRID("Híbrido"),
    OTHER("Otro");

    private final String displayName;

    FuelType(String displayName) {
        this.displayName = displayName;
    }
}
