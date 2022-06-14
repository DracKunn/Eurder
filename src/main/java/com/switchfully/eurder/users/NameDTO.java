package com.switchfully.eurder.users;

public record NameDTO(String firstName, String lastName) {
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
