package com.example.a310finalproject;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChangeMeetingDateUnitTest {
    @Test
    public void testChangeMeetingdata() {
        Meeting testMeeting = new Meeting("Tom", "Test", "https://usc.zoom.us/j/1", "01/04/2002", "4:20", "English");
        testMeeting.setDate("01/05/2002");
        assertTrue(testMeeting.getDate() == "01/05/2002");
    }
}
