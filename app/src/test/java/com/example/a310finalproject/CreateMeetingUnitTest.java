package com.example.a310finalproject;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CreateMeetingUnitTest {

    @Test
    public void testCreateMeeting() {
        Meeting testMeeting = new Meeting("Tom", "Test", "https://usc.zoom.us/j/1", "01/04/2002", "4:20", "English");

        assertTrue(testMeeting.getHost() == "Tom");
    }
}
