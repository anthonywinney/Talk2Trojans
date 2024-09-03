package com.example.a310finalproject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateMeetingParticipantsUnitTest {

    @Test
    public void testUpdateMeetingParticipants() {
        Meeting testMeeting = new Meeting("Tom", "Test", "https://usc.zoom.us/j/1", "01/04/2002", "4:20", "English");
        testMeeting.setParticipantsList(new ArrayList<>(Arrays.asList("Tom Watson", "Tom Watson", "Tom Watson")));

        assertEquals(new ArrayList<>(Arrays.asList("Tom Watson", "Tom Watson", "Tom Watson")), testMeeting.getParticipantsList());
    }
}
