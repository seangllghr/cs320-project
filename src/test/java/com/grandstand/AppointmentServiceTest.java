package com.grandstand;

import com.grandstand.Appointment.UpdateableField;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppointmentServiceTest {

    private AppointmentService as;
    private final String TEST_DATE = "2021-12-02T12:21+02:00";
    private final String TEST_DESCRIPTION =
        "Unexpectedly precise 50 characters of description";

    @BeforeEach
    public void setUp() {
        // create a new appointment service and stuff it full of worthless data
        as = new AppointmentService();
        assertDoesNotThrow(() -> {
                for (int i = 0; i < 10; i++) {
                    as.addAppointment(TEST_DATE, TEST_DESCRIPTION);
                }
            });
    }

    @Test
    public void testGetApptById() {
        String apptId = "0000000001";
        assertEquals(apptId, as.getApptById(apptId).getId());
    }

    @Test
    public void testGetApptById_nonexistent() {
        assertThrows(NullPointerException.class,
                     () -> as.getApptById("0000000047"),
                     "Appointment ID not found");
    }

    @Test
    public void testDeleteAppt_first() {
        String apptId = "0000000001";
        assertEquals(apptId, as.getApptById(apptId).getId());
        as.deleteAppt(apptId);
        assertThrows(NullPointerException.class,
                     () -> as.getApptById(apptId),
                     "Appointment ID not found");
    }

    @Test
    public void testDeleteAppt_middle() {
        String apptId = "0000000005";
        assertEquals(apptId, as.getApptById(apptId).getId());
        as.deleteAppt(apptId);
        assertThrows(NullPointerException.class,
                     () -> as.getApptById(apptId),
                     "Appointment ID not found");
    }

    @Test
    public void testDeleteAppt_last() {
        String apptId = "0000000010";
        assertEquals(apptId, as.getApptById(apptId).getId());
        as.deleteAppt(apptId);
        assertThrows(NullPointerException.class,
                     () -> as.getApptById(apptId),
                     "Appointment ID not found");
    }

    @Test
    public void testDeleteAppt_doesNotRipple() {
        String apptId = "0000000006";
        String beforeId = "0000000005";
        String afterId = "0000000007";
        assertEquals(apptId, as.getApptById(apptId).getId());
        as.deleteAppt(apptId);
        assertThrows(NullPointerException.class,
                     () -> as.getApptById(apptId),
                     "Appointment ID not found");
        assertEquals(beforeId, as.getApptById(beforeId).getId());
        assertEquals(afterId, as.getApptById(afterId).getId());
    }

    @Test
    public void testDeleteAppt_nonexistent() {
        String apptId = "0000000042";
        assertThrows(NullPointerException.class,
                     () -> as.deleteAppt(apptId),
                     "Appointment ID not found");
    }

    @Test
    public void testUpdateApptDate() {
        String apptId = "0000000008";
        String newDate = "2021-03-11T17:00-06:00";
        as.updateAppt(apptId, UpdateableField.DATE, newDate);
        assertEquals(newDate, as.getApptById(apptId).getDate().toString());
    }

    @Test
    public void testUpdateApptDescription() {
        String apptId = "0000000004";
        String newDesc = "This appointment has a new description";
        as.updateAppt(apptId, UpdateableField.DESCRIPTION, newDesc);
        assertEquals(newDesc, as.getApptById(apptId).getDescription());
    }

    @Test
    public void testUpdateApptDescription_nonexistent() {
        String apptId = "0000000024";
        String newDesc = "This should error out";
        assertThrows(NullPointerException.class,
                     () -> as.updateAppt(apptId, UpdateableField.DESCRIPTION, newDesc),
                     "Appointment ID not found");
    }

    @Test
    public void testUpdateAppt_invalidDateString() {
        String apptId = "0000000001";
        String newDate = "2021-03-03";
        assertThrows(IllegalArgumentException.class,
                     () -> as.updateAppt(apptId, UpdateableField.DATE, newDate),
                     "DateTime string must consist of date, time, and timezone offset");
    }

}
