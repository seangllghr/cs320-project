package com.grandstand;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService cs;
    private final String TEST_NAME = "Testy";
    private final String TEST_SURNAME = "McTestface";
    private final String TEST_PHONE = "5555555555";
    private final String TEST_ADDRESS = "123 Test Street, Madison, WI 53711";

    @BeforeEach
    public void setUp() {
        cs = new ContactService();
        assertDoesNotThrow(() -> {
                cs.addContact(TEST_NAME, TEST_SURNAME, TEST_PHONE, TEST_ADDRESS);
            for (int i = 0; i < 10; i++) {
                cs.addContact(TEST_NAME, TEST_SURNAME, TEST_PHONE, TEST_ADDRESS);
            }
            });
    }

    // NOTE: All of these tests necessarily depend on ContactService.getContactById().

    @Test
    public void testAddManyContacts() {
        Contact fifthContact = cs.getContactById("0000000005");
        assertEquals("0000000005", fifthContact.getId());
    }

    @Test
    public void testGetContactById() {
        Contact testContact = cs.getContactById("0000000001");
        assertEquals("0000000001", testContact.getId());
    }

    @Test
    public void testGetContactById_empty() {
        assertThrows(NullPointerException.class,
                     () -> cs.getContactById("0000000042"),
                     "Contact ID not found");
    }

}
