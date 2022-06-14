package com.switchfully.eurder.users;

import com.switchfully.eurder.users.admin.Admin;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Repository
public class UserRepository {
//    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private Map<String, User> userMap;

    public UserRepository(){this.userMap = createAndInitializeMemberMap();}

    private Map<String, User> createAndInitializeMemberMap() {
        User admin = new Admin(new Name("Brecht", "Van Rie"),"brecht.vanrie@gmail.com");
        userMap = new HashMap<>();
        userMap.put(admin.email,admin);
        return userMap;
    }
}
