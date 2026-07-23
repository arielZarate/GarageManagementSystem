package com.arielzarate.GarageManagementSystem.interfaces.errors.model;

public record ApplicationError(
    int status,
    String message,
    String type
) {

    public static ApplicationError badRequest(String message) {
        return new ApplicationError(400, "Parametros incorrectos : " + message, "error");
    }

    public static ApplicationError serverError(Throwable origin) {
        return new ApplicationError(500, "Error interno del servidor " + origin.getMessage(), "error");
    }

    public static ApplicationError notNullError(String field) {
        return new ApplicationError(400, field + " No puede ser null o vacio", "error");
    }

    public static ApplicationError notFoundError(String message) {
        return new ApplicationError(404, message, "error");
    }

    public static ApplicationError conflict(String message) {
        return new ApplicationError(409, message, "error");
    }

    public String getMessage() {
        return "ApplicationError{estado=" + status + ", mensaje='" + message + "', tipo='" + type + "'}";
    }
}
