package com.switchfully.eurder.users;

import com.switchfully.eurder.users.admin.Admin;
import com.switchfully.eurder.users.customer.Customer;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


@Repository
public class UserRepository {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private Map<String, User> userMap;
    private Map<String,Customer> customerMap;

    public UserRepository(){this.userMap = createAndInitializeMemberMap();}

    private Map<String, User> createAndInitializeMemberMap() {
        User admin = new Admin(new Name("Brecht", "Van Rie"),"brecht.vanrie@gmail.com");
        userMap = new HashMap<>();
        userMap.put(admin.email,admin);
        return userMap;
    }

    public Customer addNewCustomer(Customer customer) {
        userMap.put(customer.email, customer);
        customerMap.put(customer.email,customer);
        logger.info("User "+customer+" has been added.");
        return customer;
    }


    public User getUserByEmail(String email) {
        return userMap.get(email);
    }

    public List<Customer> getAllCustomers() {
        return customerMap.values().stream().toList();
    }
}
