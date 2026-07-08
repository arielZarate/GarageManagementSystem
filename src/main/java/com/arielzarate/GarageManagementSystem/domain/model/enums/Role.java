package com.arielzarate.GarageManagementSystem.domain.model.enums;

import lombok.Getter;

@Getter
public enum Role {
    ADMIN("Admin"),
    MANAGER("Gerente"),
    SALES("Ventas"),
    MECHANIC("Mecánico");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }
}
