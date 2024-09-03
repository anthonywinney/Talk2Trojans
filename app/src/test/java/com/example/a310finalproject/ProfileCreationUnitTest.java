package com.example.a310finalproject;

import org.junit.Test;

import static org.junit.Assert.*;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProfileCreationUnitTest {

    @Test
    public void testProfileCreation() {
        String email = "unittest@usc.edu";
        String password = "test";
        String firstName = "Unit";
        String lastName = "Test";
        String speakerStatus = "Native";

        UserType user = new UserType(email, password, firstName, lastName, speakerStatus);
        new CurrentUser(user);

        assertTrue(CurrentUser.currentUser.getmEmail() == email);
    }

}
