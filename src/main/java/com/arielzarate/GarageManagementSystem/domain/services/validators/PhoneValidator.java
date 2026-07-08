package com.arielzarate.GarageManagementSystem.domain.services.validators;

import java.util.regex.Pattern;

public class PhoneValidator {

    private static final Pattern PHONE_PATTERN = Pattern.compile(
            "^(?:\\+54)?\\s?351\\s?\\d{6,8}$"
    );

    private PhoneValidator() {}

    public static void validate(String phone) {
        if (phone == null || !PHONE_PATTERN.matcher(phone.trim()).matches()) {
            throw new IllegalArgumentException(
                    "El teléfono debe ser un número válido de Córdoba. Ej: 351 1234567"
            );
        }
    }

    public static boolean isValid(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone.trim()).matches();
    }

    public static String normalize(String phone) {
        if (phone == null) return null;
        String cleaned = phone.replaceAll("\\s+", "");
        return cleaned.startsWith("+54") ? cleaned : "+54" + cleaned;
    }
}
