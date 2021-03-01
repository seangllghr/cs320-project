package com.grandstand;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppointmentTest {

    private Appointment appt = null;

    @BeforeEach
    public void setUp() {
        assertDoesNotThrow(() -> appt = new Appointment("1234567890"));
    }

    @Test
    public void testNewAppt_bad() {
        assertThrows(IllegalArgumentException.class,
                     () -> new Appointment("ThisIDIsClearlyFarTooLong"),
                     "Invalid ID string");
    }

    @Test
    public void testNewAppt_null() {
        assertThrows(NullPointerException.class, () -> new Appointment(null),
                     "Appointment ID cannot be null");
    }

    @Test
    public void testGetId() {
        assertEquals("1234567890", appt.getId());
    }

    @Test
    public void testGetDate() {
        // We don't know exactly when the appt was created, so we just want to
        // make sure getDate() returns something in the right ballpark
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime apptDateTime = appt.getDate();
        int nanoDifference = now.getNano() - apptDateTime.getNano();
        // It's reasonably safe to assume the test will execute in under
        // *Dr. Evil pinky flourish* ONE MILLION nanoseconds! (0.001s)
        assertTrue(nanoDifference < 1000000);
    }

    @Test
    public void testSetDate() {
        String newDateString = "2021-12-02T20:21-12:00";
        OffsetDateTime newDateTime = OffsetDateTime.parse(newDateString);
        appt.setDate(newDateTime);
        assertEquals(newDateString, appt.getDate().toString());
    }

    @Test
    public void testSetDate_past() {
        String newDateString = "2020-02-02T20:20-02:00";
        OffsetDateTime newDateTime = OffsetDateTime.parse(newDateString);
        assertThrows(IllegalArgumentException.class,
                     () -> appt.setDate(newDateTime),
                     "Appointment date must be in the future");
    }

    @Test
    public void testSetDate_null() {
        assertThrows(NullPointerException.class,
                     () -> appt.setDate(null),
                     "Appointment date cannot be null");
    }

    @Test
    public void testGetDescription() {
        assertEquals("", appt.getDescription());
    }

    @Test
    public void testSetDescription() {
        String newDescription = "This appointment description is longer.";
		appt.setDescription(newDescription);
        assertEquals(newDescription, appt.getDescription());
    }

    @Test
    public void testSetDescription_maximum() {
        // We're going to reuse this string, because it's convenient
        String newDescription = "A description which fits the specified max length.";
		appt.setDescription(newDescription);
        assertEquals(newDescription, appt.getDescription());
    }

    @Test
    public void testSetDescription_empty() {
		appt.setDescription("This description should be temporary.");
		appt.setDescription("");
        assertEquals("", appt.getDescription());
    }

    @Test
    public void testSetDescription_unicode() {
        // Some Latin diacritics.
        String newDescription = "Essa descrição têm diacriticos";
		appt.setDescription(newDescription);
        assertEquals(newDescription, appt.getDescription());
        // I'm given to understand that this is my standard non-Latin description
        // in Farsi.
        newDescription = "این توصیف شامل حروف غیر لاتین است";
		appt.setDescription(newDescription);
        assertEquals(newDescription, appt.getDescription());
    }

    @Test
    public void testSetDescription_overlength() {
        String newDescription =
                "This description must be much longer than 50 characters.";
        assertThrows(IllegalArgumentException.class,
                     () -> appt.setDescription(newDescription),
                     "Appointment description must not exceed 50 characters");
    }

    @Test
    public void testSetDescription_null() {
        assertThrows(NullPointerException.class,
                     () -> appt.setDescription(null),
                     "Appointment description cannot be null");
    }

}
