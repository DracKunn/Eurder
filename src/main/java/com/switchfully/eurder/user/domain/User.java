package com.switchfully.eurder.user.domain;

import com.switchfully.eurder.util.address.domain.Address;
import com.switchfully.eurder.util.name.domain.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

import static com.switchfully.eurder.util.validation.ValidatorsUtility.*;

@Entity
@Table(name = "user", schema = "eurder")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_id_seq", allocationSize = 1)
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Embedded
    private Name name;
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "fk_address_id")
    @OneToOne(cascade = CascadeType.ALL)
    private Address address;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role userRole;

    public User(String userName, Name name, String email, Address address, String phoneNumber, Role role) throws IllegalArgumentException {
        this.userName = validateURLFriendly(userName);
        this.name = validateName(name);
        this.email = validateEmail(email);
        this.address = validateAddress(address);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
        this.userRole = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && Objects.equals(getUserName(), user.getUserName()) && Objects.equals(getName(), user.getName()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getAddress(), user.getAddress()) && Objects.equals(getPhoneNumber(), user.getPhoneNumber()) && getUserRole() == user.getUserRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getName(), getEmail(), getAddress(), getPhoneNumber(), getUserRole());
    }
}
