package com.arielzarate.GarageManagementSystem.domain.services;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringCapitalize {

    public static String capitalize(String text) {
        return Arrays.stream(text.trim().split("\\s+"))
                .filter(word -> !word.isEmpty())
                .map(word -> Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase())
                .collect(Collectors.joining(" "));
    }
}
