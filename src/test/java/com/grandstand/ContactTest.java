package com.grandstand;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactTest {
    private Contact contact;

    @BeforeEach
    public void setUp() {
        contact = new Contact("1234567890");
    }

    @Test
    public void testGetId() {
        assertEquals("1234567890", contact.getId());
    }

    @Test
    public void testGetFirstName() {
        assertEquals("", contact.getFirstName());
    }

    @Test
    public void testSetFirstName() {
        contact.setFirstName("Testy");
        assertEquals("Testy", contact.getFirstName());
    }

    @Test
    public void testGetLastName() {
        assertEquals("", contact.getLastName());
    }

    @Test
    public void testSetLastName() {
        // Inspired by the *real* British submarine research vessel Boaty McBoatface
        contact.setLastName("McTestface");
        assertEquals("McTestface", contact.getLastName());
    }

    @Test
    public void testGetPhone() {
        assertEquals("", contact.getPhone());
    }

    @Test
    public void testSetPhone() {
        contact.setPhone("1111111111");
        assertEquals("1111111111", contact.getPhone());
    }

    @Test
    public void testGetAddress() {
        assertEquals("", contact.getAddress());
    }

    @Test
    public void testSetAddress() {
        // This address actually appears on rentable.com. I think it's an oversight.
        contact.setAddress("123 Test Street, Madison, WI 53711");
        assertEquals("123 Test Street, Madison, WI 53711", contact.getAddress());
    }

}
