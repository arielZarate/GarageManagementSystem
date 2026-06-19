package com.arielzarate.GarageManagementSystem.domain.services;

import java.util.function.Supplier;

public class CodeGenerator {

    private static final int PAD_LENGTH = 4;

    public static String generate(String prefix, Supplier<Long> countSupplier) {
        long count = countSupplier.get();
        return prefix + String.format("%0" + PAD_LENGTH + "d", count + 1);
    }

    public static String generateEmployee(Supplier<Long> countSupplier) {
        return generate("EMP-", countSupplier);
    }

    public static String generateCustomer(Supplier<Long> countSupplier) {
        return generate("CLI-", countSupplier);
    }

}
