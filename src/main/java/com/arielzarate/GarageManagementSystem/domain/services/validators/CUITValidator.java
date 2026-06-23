package com.arielzarate.GarageManagementSystem.domain.services.validators;

import java.util.regex.Pattern;

public class CUITValidator {

    private static final Pattern CUIT_PATTERN = Pattern.compile("^\\d{2}-\\d{8}-\\d$|^\\d{11}$");

    private CUITValidator() {}

    public static void validate(String cuit) {
        if (cuit == null || !CUIT_PATTERN.matcher(cuit).matches()) {
            throw new IllegalArgumentException(
                    "El CUIT debe tener formato XX-XXXXXXXX-X o 11 dígitos numéricos"
            );
        }
    }

    public static boolean isValid(String cuit) {
        return cuit != null && CUIT_PATTERN.matcher(cuit).matches();
    }
}
