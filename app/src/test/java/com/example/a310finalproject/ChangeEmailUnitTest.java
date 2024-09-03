package com.example.a310finalproject;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
public class ChangeEmailUnitTest {
    @Test
    public void testChangeEmail() {
        String email = "unittest@usc.edu";
        String password = "test";
        String firstName = "Unit";
        String lastName = "Test";
        String speakerStatus = "Native";

        UserType user = new UserType(email, password, firstName, lastName, speakerStatus);
        user.setmEmail("newemail@usc.edu");
        new CurrentUser(user);

        assertTrue(CurrentUser.currentUser.getmEmail() == "newemail@usc.edu");
    }
}
