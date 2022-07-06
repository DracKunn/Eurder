package com.switchfully.eurder.util.address.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_id_seq", allocationSize = 1)
    private int id;
    @Column(name = "street_name")
    private String streetName;
    @Column(name = "street_number")
    private int streetNumber;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "city")
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;
        return getId() == address.getId() && getStreetNumber() == address.getStreetNumber() && Objects.equals(getStreetName(), address.getStreetName()) && Objects.equals(getPostalCode(), address.getPostalCode()) && Objects.equals(getCity(), address.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStreetName(), getStreetNumber(), getPostalCode(), getCity());
    }
}
