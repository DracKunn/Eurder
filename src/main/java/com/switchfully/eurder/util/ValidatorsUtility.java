package com.switchfully.eurder.util;

public class ValidatorsUtility {
    private ValidatorsUtility(){
        throw new IllegalStateException("Utility class");
    }
    public static void isNotNullOrEmpty(String stringToValidate, String variableFieldName) {
        if (stringToValidate == null || stringToValidate.isBlank()) {
            throw new IllegalArgumentException(variableFieldName + " cannot be empty");
        }
    }
}
