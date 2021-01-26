package com.grandstand;

import java.util.regex.Pattern;

/**
 * The Contact class implements the data backend for the ContactService
 * <p>
 * Several adaptations from the original spec have been made. Notably, the
 * <code>firstName</code>, <code>lastName</code>, and <code>address</code> data
 * members have seen expansions of their maximum lengths. This is done to better
 * accomodate real-world data; the specifics are noted before validation in the
 * respective setters.
 *
 * @author Sean Gallagher
 */
public class Contact {
    private final String ID;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    /**
     * Initialize an empty Contact with ID <code>contactId</code>
     *
     * @param contactId an alphanumeric ID string of 10 or fewer characters
     */
    public Contact(String contactId) throws IllegalArgumentException {
        if (contactId.matches("[0-9A-Za-z]+")) {
            this.ID = contactId;
        } else {
            throw new IllegalArgumentException("Invalid ID string.");
        }
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
        this.address = "";
    }

    public String getId() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName)
        throws IllegalArgumentException, NullPointerException {
        // Expanding to 12 characters allows us to cover an astonishing majority
        // of the names listed on Wikipedia's lists of most common given names.
        if (firstName == null) {
            throw new NullPointerException("Contact first name cannot be null");
        }
        if (firstName.length() <= 12) {
                this.firstName = firstName;
        } else {
            String errorMessage = "Contact first name must not exceed 12 characters";
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName)
        throws IllegalArgumentException, NullPointerException {
        // Similarly, adding an extra 5 characters expands our capability to a
        // breathtaking majority of the world's surnames
        if (lastName == null) {
            throw new NullPointerException("Contact last name cannot be null");
        }
        if (lastName.length() <= 15) {
            this.lastName = lastName;
        } else {
            String errorMessage = "Contact last name must not exceed 15 characters";
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) throws
        IllegalArgumentException, NullPointerException {
        // International numbers are bafflingly complex, so we're sticking with
        // US-standard NANP 10-digit numbers, as specified.
        if (phone == null) {
            throw new NullPointerException("Contact phone cannot be null");
        }
        if (Pattern.matches("(\\A\\d{10}+\\Z)?", phone)) {
            this.phone = phone;
        } else {
            String errorMessage = "Contact phone must be exactly 10 digits (0-9)";
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address)
        throws IllegalArgumentException, NullPointerException {
        // 30 characters isn't really enough for a full US street address,
        // assuming we're including city, state, and ZIP. I've expanded it to 50
        // characters. Aside from length, addresses may consist of any Unicode
        // character and can span multiple lines.
        if (address == null) {
            throw new NullPointerException("Contact address cannot be null");
        }
        if (address.length() <= 50) {
            this.address = address;
        } else {
            String errorMessage = "Contact address must be less than 50 characters";
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
