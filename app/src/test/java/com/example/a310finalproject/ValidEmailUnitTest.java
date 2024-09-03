package com.example.a310finalproject;

import org.junit.Test;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

public class ValidEmailUnitTest {

    @Test
    public void testValidEmail() {
        String validEmail = "tfwatson@usc.edu";
        String invalidEmail = "tfwatson@gmail.com";

        // Logic used in login and registration
        assertFalse(validEmail.isEmpty());
        assertTrue(Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")
                .matcher(validEmail).matches());
        assertTrue(Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@usc\\.edu").matcher(validEmail).matches());
        assertFalse(validEmail.contains("#") || validEmail.contains("$")
                || validEmail.contains("[") || validEmail.contains("]")
                || validEmail.substring(validEmail.indexOf(".")+1).contains("."));

        assertFalse(Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@usc\\.edu").matcher(invalidEmail).matches());
    }
}
