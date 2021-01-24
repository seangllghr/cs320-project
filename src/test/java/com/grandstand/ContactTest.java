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
    public void testSetFirstName_simple() {
        contact.setFirstName("Testy");
        assertEquals("Testy", contact.getFirstName());
    }

    @Test public void testSetFirstName_empty() {
        contact.setFirstName("");
        assertEquals("", contact.getFirstName());
    }

    @Test
    public void testSetFirstName_maximum() {
        contact.setFirstName("Theophrastos"); // VP Spiro Agnew's father's Greek name
        assertEquals("Theophrastos", contact.getFirstName());
    }

    @Test
    public void testSetFirstName_diacritics() {
        contact.setFirstName("Seán"); // The Irish spelling of my first name
        assertEquals("Seán", contact.getFirstName());
    }

    @Test
    public void testSetFirstName_nonLatin() {
        contact.setFirstName("بطرس"); // Arabic "Boutros" (Peter). Also RTL, which is fun
        assertEquals("بطرس", contact.getFirstName());
    }

    @Test
    public void testSetFirstName_null() {
        Exception ex = assertThrows(NullPointerException.class, () -> {
                contact.setFirstName(null);
            });
        assertEquals("Contact first name cannot be null", ex.getMessage());
    }

    @Test
    public void testSetFirstName_overlength() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
                contact.setFirstName("Oluwatamilore"); // a Yoruba name, from west Africa
            });
        assertEquals("Contact first name must not exceed 12 characters", ex.getMessage());
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
    public void testSetLastName_empty() {
        contact.setLastName("");
        assertEquals("", contact.getLastName());
    }

    @Test
    public void testSetLastName_maximum() {
        contact.setLastName("Anagnostopoulos"); // Agnew's father's original surname
        assertEquals("Anagnostopoulos", contact.getLastName());
    }

    @Test
    public void testSetLastName_diacritics() {
        contact.setLastName("Ó Gallchobhair"); // Irish spelling of my surname
        assertEquals("Ó Gallchobhair", contact.getLastName());
    }

    @Test
    public void testSetLastName_nonLatin() {
        contact.setLastName("ভট্টাচার্য"); // Bhattacharya, but in Bengali
        assertEquals("ভট্টাচার্য", contact.getLastName());
    }

    @Test
    public void testSetLastName_null() {
        Exception ex = assertThrows(NullPointerException.class, () -> {
                contact.setLastName(null);
            });
        assertEquals("Contact last name cannot be null", ex.getMessage());
    }

    @Test
    public void testSetLastName_overlength() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
                // This (faux) surname is lovingly stolen from Hasan Minhaj's
                // comedy special, Homecoming King.
                contact.setLastName("Rengatramanajananam");
            });
        assertEquals("Contact last name must not exceed 15 characters", ex.getMessage());
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
