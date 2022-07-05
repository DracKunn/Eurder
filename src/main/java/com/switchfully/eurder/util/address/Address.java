package com.switchfully.eurder.util.address;

import java.util.Objects;

public final class Address {
    private int id;
    private final String streetName;
    private final int streetNumber;
    private final String postalCode;
    private final String city;

    public Address(String streetName, int streetNumber, String postalCode, String city) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public String toString() {
        return streetName + " " + streetNumber + ", " + postalCode + " " + city;
    }

    public String streetName() {
        return streetName;
    }

    public int streetNumber() {
        return streetNumber;
    }

    public String postalCode() {
        return postalCode;
    }

    public String city() {
        return city;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Address) obj;
        return Objects.equals(this.streetName, that.streetName) &&
                this.streetNumber == that.streetNumber &&
                Objects.equals(this.postalCode, that.postalCode) &&
                Objects.equals(this.city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, streetNumber, postalCode, city);
    }

}
