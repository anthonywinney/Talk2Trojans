package com.example.a310finalproject;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;

public class AddFriendUnitTest {

    @Test
    public void testAddFriend() {
        String email = "unittest@usc.edu";
        String password = "test";
        String firstName = "Unit";
        String lastName = "Test";
        String speakerStatus = "Native";

        UserType user = new UserType(email, password, firstName, lastName, speakerStatus);

        email = "friend@usc.edu";
        password = "friend";
        firstName = "Friend";
        lastName = "Afriend";
        speakerStatus = "Native";
        UserType friend = new UserType(email, password, firstName, lastName, speakerStatus);

        ArrayList<String> friendList = new ArrayList<String>();
        friendList.add(firstName+lastName);

        user.setmFriendsList(friendList);

        new CurrentUser(user);




        assertTrue(CurrentUser.currentUser.getmFriendsList().get(0).equals(firstName+lastName));
    }

}


