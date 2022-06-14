package com.switchfully.eurder.users;

public class Name {
    private final String firstName;
    private final String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = validateNameField(firstName);
        this.lastName = validateNameField(lastName);
    }

    private String validateNameField(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name cannot be empty!");
        }
        return name;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
