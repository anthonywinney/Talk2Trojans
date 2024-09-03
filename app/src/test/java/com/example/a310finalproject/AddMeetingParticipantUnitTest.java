package com.example.a310finalproject;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;

public class AddMeetingParticipantUnitTest {
    @Test
    public void testAddMeetingParticipant() {
        Meeting testMeeting = new Meeting("Tom", "Test", "https://usc.zoom.us/j/1", "01/04/2002", "4:20", "English");

        String email = "unittest@usc.edu";
        String password = "test";
        String firstName = "Unit";
        String lastName = "Test";
        String speakerStatus = "Native";

        UserType user = new UserType(email, password, firstName, lastName, speakerStatus);

        ArrayList<String> participantsList = new ArrayList<String>();
        participantsList.add("Jerry");

        testMeeting.setParticipantsList(participantsList);

        assertTrue(testMeeting.getParticipantsList().get(0).equals("Jerry"));
    }
}
