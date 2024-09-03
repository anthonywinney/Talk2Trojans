package com.example.a310finalproject;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ChangeMeetingLanguageUnitTest {
    @Test
    public void testChangeMeetingLanguage() {
        Meeting testMeeting = new Meeting("Tom", "Test", "https://usc.zoom.us/j/1", "01/04/2002", "4:20", "English");
        testMeeting.setmLanguage("Spanish");
        assertTrue(testMeeting.getmLanguage() == "Spanish");
    }
}
