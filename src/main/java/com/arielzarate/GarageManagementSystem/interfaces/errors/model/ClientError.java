package com.arielzarate.GarageManagementSystem.interfaces.errors.model;

public record ClientError(
        String type,
        String title,
        int status,
        String detail,
        String instance
) {
}
