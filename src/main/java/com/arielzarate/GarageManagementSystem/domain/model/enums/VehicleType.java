package com.arielzarate.GarageManagementSystem.domain.model.enums;

import lombok.Getter;

@Getter
public enum VehicleType {
    MOTORCYCLE("Moto"),
    CAR("Auto"),
    PICKUP("Camioneta"),
    TRUCK("Camión"),
    VAN("Furgón"),
    OTHER("Otro");

    private final String displayName;

    VehicleType(String displayName) {
        this.displayName = displayName;
    }


}
