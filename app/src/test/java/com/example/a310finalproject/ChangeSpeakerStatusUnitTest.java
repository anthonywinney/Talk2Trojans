package com.example.a310finalproject;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChangeSpeakerStatusUnitTest {

    @Test
    public void testChangeFirstName() {
        String email = "unittest@usc.edu";
        String password = "test";
        String firstName = "Unit";
        String lastName = "Test";
        String speakerStatus = "Native";

        UserType user = new UserType(email, password, firstName, lastName, speakerStatus);
        user.setmType("International");
        new CurrentUser(user);

        assertTrue(CurrentUser.currentUser.getmType() == "International");
    }


}
