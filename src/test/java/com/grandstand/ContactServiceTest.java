package com.grandstand;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.grandstand.Contact.UpdateableField;

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
    public void testAddBadContact() {
        // If we demonstrate that one of each error bubbles up, it's fair to
        // assume that others will, too.
        assertThrows(IllegalArgumentException.class, () -> {
                cs.addContact(TEST_NAME, TEST_SURNAME, "123-badnum", TEST_ADDRESS);
            }, "Contact phone must be exactly 10 digits (0-9)");
        assertThrows(NullPointerException.class, () -> {
                cs.addContact(null, TEST_SURNAME, TEST_PHONE, TEST_ADDRESS);
            }, "Contact first name cannot be null");
    }

    @Test
    public void testGetContactById() {
        Contact testContact = cs.getContactById("0000000001");
        assertEquals("0000000001", testContact.getId());
    }

    @Test
    public void testGetNonexistentContact() {
        assertThrows(NullPointerException.class,
                     () -> cs.getContactById("0000000042"),
                     "Contact ID not found");
    }

    @Test
    public void testDeleteContact_first() {
        String contactId = "0000000001";
		assertEquals(contactId, cs.getContactById(contactId).getId());
        assertDoesNotThrow(() -> cs.deleteContact(contactId));
        assertThrows(NullPointerException.class,
                     () -> cs.getContactById(contactId),
                     "Contact ID not found");
    }

    @Test
    public void testDeleteContact_middle() {
        String contactId = "0000000005";
		assertEquals(contactId, cs.getContactById(contactId).getId());
        assertDoesNotThrow(() -> cs.deleteContact(contactId));
        assertThrows(NullPointerException.class,
                     () -> cs.getContactById(contactId),
                     "Contact ID not found");
    }

    @Test
    public void testDeleteContact_last() {
        String contactId = "0000000011";
		assertEquals(contactId, cs.getContactById(contactId).getId());
        assertDoesNotThrow(() -> cs.deleteContact(contactId));
        assertThrows(NullPointerException.class,
                     () -> cs.getContactById(contactId),
                     "Contact ID not found");
    }

    @Test
    public void testDeleteContact_doesNotRipple() {
        // NOTE: In case the title isn't clear, this test asserts that deletion
        // of a contact does not affect the contacts before and after it
        String contactId = "0000000005";
        String contactIdBefore = "0000000004";
        String contactIdAfter = "0000000006";
		assertEquals(contactId, cs.getContactById(contactId).getId());
        assertDoesNotThrow(() -> cs.deleteContact(contactId));
		assertEquals(contactIdBefore, cs.getContactById(contactIdBefore).getId());
		assertEquals(contactIdAfter, cs.getContactById(contactIdAfter).getId());
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
        cs.updateContact(contactId, UpdateableField.FIRST_NAME, newName);
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
        cs.updateContact(contactId, UpdateableField.PHONE, newPhone);
        assertEquals(newPhone, cs.getContactById(contactId).getPhone());
    }

    @Test
    public void testUpdateContactAddress() {
        String contactId = "0000000008";
        String newAddress = "456 Some Other Street, Anytown, KY 09876";
        assertEquals(TEST_ADDRESS, cs.getContactById(contactId).getAddress());
        cs.updateContact(contactId, UpdateableField.ADDRESS, newAddress);
        assertEquals(newAddress, cs.getContactById(contactId).getAddress());
    }

    @Test
    public void testUpdateBadContact() {
        String contactId = "0000000047";
        String newName = "Jim";
        assertThrows(NullPointerException.class, () -> {
                cs.updateContact(contactId, UpdateableField.FIRST_NAME, newName);
            }, "Contact ID not found");
    }

    @Test
    public void TestUpdateContact_badValue() {
        // Like with addContact, if we get one of each error, we should get
        // all of them
        String contactId = "0000000007";
        String newName = "Extralongfirstname";

        assertThrows(IllegalArgumentException.class, () -> {
                cs.updateContact(contactId, UpdateableField.FIRST_NAME, newName);
            }, "Contact first name must not exceed 12 characters");
        assertThrows(NullPointerException.class, () -> {
                cs.updateContact(contactId, UpdateableField.ADDRESS, null);
            }, "Contact address cannot be null");
    }

}
