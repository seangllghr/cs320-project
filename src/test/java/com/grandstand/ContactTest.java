package com.grandstand;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.NullPointerException;

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
    public void testSetLastName_simple() {
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
    public void testSetPhone_simple() {
        contact.setPhone("1234567890");
        assertEquals("1234567890", contact.getPhone());
    }

    @Test
    public void testSetPhone_empty() {
        contact.setPhone("");
        assertEquals("", contact.getPhone());
    }

    @Test
    public void testSetPhone_short() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
                contact.setPhone("4567890");
            });
        assertEquals("Contact phone must be exactly 10 digits (0-9)", ex.getMessage());
    }

    @Test
    public void testSetPhone_null() {
        Exception ex = assertThrows(NullPointerException.class, () -> {
                contact.setPhone(null);
            });
        assertEquals("Contact phone cannot be null", ex.getMessage());
    }

    @Test
    public void testSetPhone_letters() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
                contact.setPhone("123456789Q");
            });
        assertEquals("Contact phone must be exactly 10 digits (0-9)", ex.getMessage());
    }

    @Test
    public void testGetAddress() {
        assertEquals("", contact.getAddress());
    }

    @Test
    public void testSetAddress_simple() {
        // This address actually appears on rentable.com. I think it's an oversight.
        contact.setAddress("123 Test Street, Madison, WI 53711");
        assertEquals("123 Test Street, Madison, WI 53711", contact.getAddress());
    }

    @Test
    public void testSetAddress_multiline() {
        contact.setAddress("123 Test Street\nMadison, WI 53711");
        assertEquals("123 Test Street\nMadison, WI 53711", contact.getAddress());
    }

    @Test
    public void testSetAddress_international() {
        // This is the address of the Tokyo Central Post Office
        String address = "〒100-8994東京都千代田区丸ノ内二丁目7番2号東京中央郵便局";
        contact.setAddress(address);
        assertEquals(address, contact.getAddress());
    }

    @Test
    public void testSetAddress_internationalMultiline() {
        // This is the three-line form of the above address
        String address = "〒100-8994\n東京都千代田区丸ノ内二丁目7番2号\n東京中央郵便局";
        contact.setAddress(address);
        assertEquals(address, contact.getAddress());
    }

    @Test
    public void testSetAddress_null() {
        Exception ex = assertThrows(NullPointerException.class, () -> {
                contact.setAddress(null);
            });
        assertEquals("Contact address cannot be null", ex.getMessage());
    }

    @Test
    public void testSetAddress_overlength() {
        String bigAddress = "12345 Very Long Address Square, Los Nombres Grandes, CA 12345";
        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
                contact.setAddress(bigAddress);
            });
        assertEquals("Contact address must be less than 50 characters", ex.getMessage());
    }

}
