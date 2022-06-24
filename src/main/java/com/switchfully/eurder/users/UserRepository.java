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
    private Map<String, Customer> customerMap;

    public UserRepository() {
        this.userMap = createAndInitializeUserMap();
        this.customerMap = createCustomerMap();
    }

    private Map<String, User> createAndInitializeUserMap() {
        User admin = new Admin("B-rex",new Name("Brecht", "Van Rie"), "brecht.vanrie@gmail.com");
        userMap = new HashMap<>();
        userMap.put(admin.userName, admin);
        return userMap;
    }

    private Map<String, Customer> createCustomerMap() {
        customerMap = new HashMap<>();
        return customerMap;
    }

    public Customer addNewCustomer(Customer customer) {
        userMap.put(customer.userName, (User) customer);
        customerMap.put(customer.userName, customer);
//        logger.info("User " + customer + " has been added.");
        return customer;
    }


    public Admin getAdminByUserName(String userName) {
        return (Admin) userMap.get(userName);
    }

    public Customer getCustomerByUserName(String userName){return customerMap.get(userName);}

    public List<Customer> getAllCustomers() {
        return customerMap.values().stream().toList();
    }


}
