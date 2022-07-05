package com.switchfully.eurder.users.admin;

import com.switchfully.eurder.util.name.domain.Name;
import com.switchfully.eurder.user.domain.admin.Admin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AdminTest {
    @Test
    @DisplayName("given a bad username when creating a user then trhow exception")
    void givenABadUsernameWhenCreatingAUserThenTrhowException() {

        //given
        String username = "Bruenor the terrible url";
        Name name = new Name("Bruenor", "The Bard");
        String email = "bruenor@bardcollege.org";
        //when /then
        assertThrows(IllegalArgumentException.class, ()-> new Admin(username,name, email));
    }

}
