package com.switchfully.eurder.users;

public record Name(String firstName, String lastName) {
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
