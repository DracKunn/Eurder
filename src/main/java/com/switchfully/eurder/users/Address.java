package com.switchfully.eurder.users;

public record Address(String streetName,int streetNumber,String postalCode,String city) {
    @Override
    public String toString() {
        return streetName +" " + streetNumber + ", " + postalCode +" " +city;
    }
}
