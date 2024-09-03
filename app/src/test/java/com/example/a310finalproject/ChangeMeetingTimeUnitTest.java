package com.example.a310finalproject;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChangeMeetingTimeUnitTest {
    @Test
    public void testChangeMeetingTime() {
        Meeting testMeeting = new Meeting("Tom", "Test", "https://usc.zoom.us/j/1", "01/04/2002", "4:20", "English");
        testMeeting.setTime("4:30");
        assertTrue(testMeeting.getTime() == "4:30");
    }
}
