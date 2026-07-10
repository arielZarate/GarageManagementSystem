package com.arielzarate.GarageManagementSystem.interfaces.rest.constants;

public enum Country {

    ARGENTINA("Argentina"),
    BOLIVIA("Bolivia"),
    BRASIL("Brasil"),
    CHILE("Chile"),
    COLOMBIA("Colombia"),
    ECUADOR("Ecuador"),
    //GUYANA("Guyana"),
    PARAGUAY("Paraguay"),
    PERU("Perú"),
    //SURINAM("Surinam"),
    URUGUAY("Uruguay"),
    VENEZUELA("Venezuela");

    private final String displayName;

    Country(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
