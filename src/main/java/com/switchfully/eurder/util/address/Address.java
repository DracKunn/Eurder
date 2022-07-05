package com.switchfully.eurder.util.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table
public final class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_id_seq", allocationSize = 1)
    private int id;
    @Column
    private String streetName;
    @Column
    private int streetNumber;
    @Column
    private String postalCode;
    @Column
    private String city;

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
