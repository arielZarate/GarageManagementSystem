package com.arielzarate.GarageManagementSystem.application.errors;

import lombok.Getter;

@Getter
public class ApplicationErrorException extends RuntimeException {

    private final ApplicationError error;

    public ApplicationErrorException(ApplicationError error) {
        super(error.getMessage());
        this.error = error;
    }
}
