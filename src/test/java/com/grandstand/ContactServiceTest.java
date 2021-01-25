package com.grandstand;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    private ContactService contactService;
    private final String TEST_NAME = "Testy";
    private final String TEST_SURNAME = "McTestface";
    private final String TEST_PHONE = "5555555555";
    private final String TEST_ADDRESS = "123 Test Street, Madison, WI 53711";

    @BeforeEach
    public void setUp() {
        contactService = new ContactService();
        assertDoesNotThrow(() -> {
                contactService.addContact(TEST_NAME, TEST_SURNAME, TEST_PHONE, TEST_ADDRESS);
            });
    }

    // NOTE: All of these tests necessarily depend on ContactService.getContactById().

    @Test
    public void testAddManyContacts() {
        contactService.addContact(TEST_SURNAME, TEST_NAME, TEST_PHONE, TEST_ADDRESS);
        contactService.addContact(TEST_SURNAME, TEST_NAME, TEST_PHONE, TEST_ADDRESS);
        contactService.addContact(TEST_SURNAME, TEST_NAME, TEST_PHONE, TEST_ADDRESS);
        contactService.addContact(TEST_SURNAME, TEST_NAME, TEST_PHONE, TEST_ADDRESS);
        contactService.addContact(TEST_SURNAME, TEST_NAME, TEST_PHONE, TEST_ADDRESS);
        contactService.addContact(TEST_SURNAME, TEST_NAME, TEST_PHONE, TEST_ADDRESS);
        Contact fifthContact = contactService.getContactById("0000000005");
        assertEquals("0000000005", fifthContact.getId());
    }

    @Test
    public void testGetContactById() {
        Contact testContact = contactService.getContactById("0000000001");
        assertEquals("0000000001", testContact.getId());
    }

    @Test
    public void testGetContactById_empty() {
        Exception ex = assertThrows(NullPointerException.class, () -> {
                contactService.getContactById("0000000002");
            });
        assertEquals("Contact ID not found", ex.getMessage());
    }

}
