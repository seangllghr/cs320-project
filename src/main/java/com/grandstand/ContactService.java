package com.grandstand;

import java.util.Vector;

/**
 * A collection of contacts with facilities to manipulate the collection
 */
public class ContactService {
    private final Vector<Contact> contactList;
    private int idCounter;

    public ContactService() {
        this.contactList = new Vector<>();
        this.idCounter = 1;
    }

    /**
     * Add a new contact to the collection with the next ID from the counter
     *
     * @param firstName the contact's first name (given name)
     * @param lastName the contact's last name (surname)
     * @param phone the contact's 10-digit phone number, with no extraneous chars
     * @param address the contact's address
     * @throws IllegalArgumentException if a bad value is passed for any param
     * @throws NullPointerException if {@code null} is passed for any param
     */
    public void addContact(String firstName, String lastName,
                           String phone, String address)
        throws IllegalArgumentException, NullPointerException {

        // Generate a new contact ID from the ID counter
        String contactId = String.format("%010d", this.idCounter);

        // Assemble the new Contact
        Contact newContact = new Contact(contactId);
        newContact.setFirstName(firstName);
        newContact.setLastName(lastName);
        newContact.setPhone(phone);
        newContact.setAddress(address);

        // Add the new contact to the list
        this.contactList.add(newContact);
        this.idCounter++;
    }

    /**
     * Find a contact in the list with the specified ID and return its index
     * @param contactId the ID string to match
     * @return the index of the target contact
     * @throws IllegalArgumentException if the ID string is invalid
     * @throws NullPointerException if {@code null} is passed or ID not found
     */
    private int findContact(String contactId)
        throws IllegalArgumentException, NullPointerException {
        for (int i = 0; i < this.contactList.size(); i++) {
            String thisId = this.contactList.elementAt(i).getId();
            if (thisId.equals(contactId)) {
                return i;
            }
        }
        throw new NullPointerException("Contact ID not found");
    }

    /**
     * Retrieve a {@code Contact} object from the collection
     *
     * @param contactId the ID string of the desired contact
     * @return the {@code Contact} object matching {@code contactId}
     * @throws IllegalArgumentException if the ID string is invalid
     * @throws NullPointerException if {@code null} is passed or ID not found
     */
    public Contact getContactById(String contactId)
        throws IllegalArgumentException, NullPointerException {
        int contactIndex = findContact(contactId);
		return this.contactList.elementAt(contactIndex);
    }

    /**
     * Remove a contact from the collection
     *
     * @param contactId the ID string of the contact to remove
     * @throws IllegalArgumentException if the ID string is invalid
     * @throws NullPointerException if {@code null} is passed or ID not found
     */
    public void deleteContact(String contactId)
        throws IllegalArgumentException, NullPointerException {
        int contactIndex = findContact(contactId);
		this.contactList.remove(contactIndex);
    }

    /**
     * Update the specified field of a contact with the specified value
     *
     * @param contactId the ID string of the contact to update
     * @param field the contact field to update
     * @param value the new value for the updated field
     * @throws IllegalArgumentException if ID string or value is invalid
     * @throws NullPointerException if {@code null} is passed or ID not found
     */
    public void updateContact(String contactId, Contact.UpdateableField field, String value)
        throws IllegalArgumentException, NullPointerException {
        int contactIndex = findContact(contactId);
        switch (field) {
        case FIRST_NAME:
            this.contactList.elementAt(contactIndex).setFirstName(value);
            break;
        case LAST_NAME:
            this.contactList.elementAt(contactIndex).setLastName(value);
            break;
        case PHONE:
            this.contactList.elementAt(contactIndex).setPhone(value);
            break;
        case ADDRESS:
            this.contactList.elementAt(contactIndex).setAddress(value);
            break;
        }
    }
}
