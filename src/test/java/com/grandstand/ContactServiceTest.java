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

    @Test
    public void testDeleteContact_first() {
        assertEquals("0000000001", cs.getContactById("0000000001").getId());
        assertDoesNotThrow(() -> cs.deleteContact("0000000001"));
        assertThrows(NullPointerException.class,
                     () -> cs.getContactById("0000000001"),
                     "Contact ID not found");
    }

    @Test
    public void testDeleteContact_middle() {
        assertEquals("0000000005", cs.getContactById("0000000005").getId());
        assertDoesNotThrow(() -> cs.deleteContact("0000000005"));
        assertThrows(NullPointerException.class,
                     () -> cs.getContactById("0000000005"),
                     "Contact ID not found");
    }

    @Test
    public void testDeleteContact_last() {
        assertEquals("0000000011", cs.getContactById("0000000011").getId());
        assertDoesNotThrow(() -> cs.deleteContact("0000000011"));
        assertThrows(NullPointerException.class,
                     () -> cs.getContactById("0000000011"),
                     "Contact ID not found");
    }

    @Test
    public void testDeleteContact_doesNotRipple() {
        // NOTE: In case the title isn't clear, this test asserts that deletion
        // of a contact does not affect the contacts before and after it
        assertEquals("0000000005", cs.getContactById("0000000005").getId());
        assertDoesNotThrow(() -> cs.deleteContact("0000000005"));
        assertEquals("0000000004", cs.getContactById("0000000004").getId());
        assertEquals("0000000006", cs.getContactById("0000000006").getId());
    }

    @Test
    public void testDeleteContact_badContactId() {
        assertThrows(NullPointerException.class, () -> cs.deleteContact("0000000047"));
    }

    @Test
    public void testUpdateContactFirstName() {
        String contactId = "0000000004";
        String newName = "Brian";
        assertEquals(TEST_NAME, cs.getContactById(contactId).getFirstName());
        cs.updateContact(contactId, Contact.UpdateableField.FIRST_NAME, newName);
        assertEquals(newName, cs.getContactById(contactId).getFirstName());
    }

    @Test
    public void testUpdateContactLastName() {
        String contactId = "0000000009";
        String newSurname = "Biller";
        assertEquals(TEST_SURNAME, cs.getContactById(contactId).getLastName());
        cs.updateContact(contactId, Contact.UpdateableField.LAST_NAME, newSurname);
        assertEquals(newSurname, cs.getContactById(contactId).getLastName());
    }

    @Test
    public void testUpdateContactPhone() {
        String contactId = "0000000003";
        String newPhone = "1234567890";
        assertEquals(TEST_PHONE, cs.getContactById(contactId).getPhone());
        cs.updateContact(contactId, Contact.UpdateableField.PHONE, newPhone);
        assertEquals(newPhone, cs.getContactById(contactId).getPhone());
    }

    @Test
    public void testUpdateContactAddress() {
        String contactId = "0000000008";
        String newAddress = "456 Some Other Street, Anytown, KY 09876";
        assertEquals(TEST_ADDRESS, cs.getContactById(contactId).getAddress());
        cs.updateContact(contactId, Contact.UpdateableField.ADDRESS, newAddress);
        assertEquals(newAddress, cs.getContactById(contactId).getAddress());
    }

    @Test
    public void testUpdateBadContact() {
        String contactId = "0000000047";
        String newName = "Jim";
        assertThrows(NullPointerException.class,
                     () -> cs.updateContact(contactId,
                                            Contact.UpdateableField.FIRST_NAME,
                                            newName),
                     "Contact ID not found");
    }

}
