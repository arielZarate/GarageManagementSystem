package com.arielzarate.GarageManagementSystem.interfaces.errors.exceptions;

import com.arielzarate.GarageManagementSystem.interfaces.errors.model.ApplicationError;
import lombok.Getter;

@Getter
public class ApplicationErrorException extends RuntimeException {

    private final ApplicationError error;


    public ApplicationErrorException(ApplicationError error) {
        super(error.getMessage());
        this.error = error;
    }

}
