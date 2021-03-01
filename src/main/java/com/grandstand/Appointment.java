package com.grandstand;

import java.time.OffsetDateTime;

/**
 * Represents a single calendar appointment in memory
 */
public class Appointment {

    private final String ID;
    private OffsetDateTime apptDate;
    private String description;

    public enum UpdateableField {
        DATE, DESCRIPTION
    }

    /**
     * Create a new appointment with the specified ID. The appointment date
     * defaults to the current date and time, while the default description is
     * empty.
     *
     * @param apptId the ID string for the new appointment
     * @throws IllegalArgumentException if the supplied ID string is invalid
     * @throws NullPointerException if {@code null} is passed
     */
    public Appointment(String apptId)
        throws IllegalArgumentException, NullPointerException {
        if (apptId == null) {
            throw new NullPointerException("Appointment ID cannot be null");
        }
        if (apptId.matches("[0-9A-Za-z]{1,10}")) {
            this.ID = apptId;
        } else {
            throw new IllegalArgumentException("Invalid ID string");
        }
        this.apptDate = OffsetDateTime.now(); // default the appointment to now
        this.description = "";
    }

    public String getId() {
        return this.ID;
    }

    public OffsetDateTime getDate() {
        return this.apptDate;
    }

    /**
     * Update the appointment date value with the specified {@code OffsetDateTime}
     *
     * @param newDate a Java {@code OffsetDateTime} representing the new time
     * @throws IllegalArgumentException if the provided time is in the past
     * @throws NullPointerException if {@code null} is passed
     */
    public void setDate(OffsetDateTime newDate)
        throws IllegalArgumentException, NullPointerException {
        if (newDate == null) {
            throw new NullPointerException("Appointment date cannot be null");
        }
        if (newDate.isAfter(OffsetDateTime.now())) {
            this.apptDate = newDate;
        } else {
            String errorMessage = "Appointment date must be in the future";
			throw new IllegalArgumentException(errorMessage);
        }
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Update the appointment description value with the provided string
     *
     * @param newDescription the new appointment description
     * @throws IllegalArgumentException if the description exceeds 50 chars
     * @throws NullPointerException if {@code null} is passed
     */
    public void setDescription(String newDescription)
        throws IllegalArgumentException, NullPointerException {
        if (newDescription == null) {
            String errorMessage = "Appointment description cannot be null";
            throw new NullPointerException(errorMessage);
        }
        if (newDescription.length() <= 50) {
            this.description = newDescription;
        } else {
            String errorMessage =
                "Appointment description must not exceed 50 characters";
            throw new IllegalArgumentException(errorMessage);
        }
    }

}
