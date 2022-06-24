package com.switchfully.eurder.users.customer;

import com.switchfully.eurder.users.Address;
import com.switchfully.eurder.users.Name;

public record CustomerDTO(String userName,Name name, String email, Address address, String phoneNumber) {}


