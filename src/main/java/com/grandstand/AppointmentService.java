package com.grandstand;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.Vector;

public class AppointmentService {

    private final Vector<Appointment> apptList;
    private int idCounter;

    public AppointmentService() {
        this.apptList = new Vector<>();
        this.idCounter = 1;
    }

    /**
     * Add a new appointment to the appointment list
     *
     * @param dateTimeString An ISO 8601-compliant date/time string with
     *                       timezone offset, like "2021-06-15T15:30+01:00"
     * @param description A description of the event
     * @throws IllegalArgumentException if the date/time string is invalid, or
     *                                  the description is too long
     * @throws NullPointerException if {@code null} is passed
     */
    public void addAppointment(String dateTimeString, String description)
            throws IllegalArgumentException, NullPointerException {
        String apptId = String.format("%010d", this.idCounter);
        OffsetDateTime dateTime = parseDateTimeString(dateTimeString);

        Appointment newAppt = new Appointment(apptId);
        newAppt.setDate(dateTime);
        newAppt.setDescription(description);

        this.apptList.add(newAppt);
        this.idCounter++;
    }

    /**
     * Wrapper around {@code OffsetDateTime.parse()} with custom error handling
     *
     * @param dateTimeString an ISO 8601 date/time string with timezone offset
     * @return a Java {@code OffsetDateTime} object representing the date/time
     * @throws IllegalArgumentException if the string cannot be parsed
     */
	private OffsetDateTime parseDateTimeString(String dateTimeString)
        throws IllegalArgumentException {
		OffsetDateTime dateTime;
        try {
            dateTime = OffsetDateTime.parse(dateTimeString);
        } catch (DateTimeParseException e) {
            String errorMessage =
                "DateTime string must consist of date, time, and timezone offset";
            throw new IllegalArgumentException(errorMessage);
        }
		return dateTime;
	}

    /**
     * Find the specified appointment in the list and return its index
     *
     * @param apptId an appointment ID string to search for
     * @return the integer index of the target appointment
     * @throws IllegalArgumentException if the ID string is invalid
     * @throws NullPointerException if the appointment cannot be found or
     *                              {@code null} is passed
     */
    private int findAppt(String apptId)
        throws IllegalArgumentException, NullPointerException {
        for (int i = 0; i < this.apptList.size(); i++) {
            String thisId = this.apptList.elementAt(i).getId();
            if (thisId.equals(apptId)) {
                return i;
            }
        }
        throw new NullPointerException("Appointment ID not found");
    }

    /**
     * Find the appointment with the specified ID string
     *
     * @param apptId the ID string of the appointment to get
     * @return the {@code Appointment} object matching the provided ID
     * @throws IllegalArgumentException if the ID string is invalid
     * @throws NullPointerException if the appointment cannot be found or
     *                              {@code null} is passed
     */
    public Appointment getApptById(String apptId)
        throws IllegalArgumentException, NullPointerException {
        return this.apptList.elementAt(findAppt(apptId));
    }

    /**
     * Remove the specified appointment from the list
     *
     * @param apptId the ID string of the appointment to delete
     * @throws IllegalArgumentException if the ID string is invalid
     * @throws NullPointerException if the appointment cannot be found or
     *                              {@code null} is passed
     */
    public void deleteAppt(String apptId)
        throws IllegalArgumentException, NullPointerException {
        this.apptList.remove(findAppt(apptId));
    }

    /**
     * Update the specified field of the specified appointment with a new value
     *
     * @param apptId the ID string of the appointment to update
     * @param field the {@code Appointment.UpdateableField} to update
     * @param value the new value for the updated field
     * @throws IllegalArgumentException if the ID string or new value is invalid
     * @throws NullPointerException if the appointment cannot be found or
     *                              {@code null} is passed
     */
    public void updateAppt(String apptId, Appointment.UpdateableField field, String value)
        throws IllegalArgumentException, NullPointerException {
        int apptIndex = findAppt(apptId);
        switch (field) {
        case DATE:
            OffsetDateTime newDate = parseDateTimeString(value);
            this.apptList.elementAt(apptIndex).setDate(newDate);
            break;
        case DESCRIPTION:
            this.apptList.elementAt(apptIndex).setDescription(value);
            break;
        }
    }

}
