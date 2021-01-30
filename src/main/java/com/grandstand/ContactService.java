package com.grandstand;

import java.util.Vector;

/**
 * A collection of contacts with facilities to manipulate the collection
 */
public class ContactService {
    private Vector<Contact> contactList;
    private int idCounter;

    public ContactService() {
        this.contactList = new Vector<Contact>();
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
     * Retrieve a {@code Contact} object from the collection
     * @param contactId the ID string of the desired contact
     * @return the {@code Contact} object matching {@code contactId}
     * @throws NullPointerException if the target contact cannot be found
     */
    public Contact getContactById(String contactId) throws NullPointerException {
        for (int i = 0; i < this.contactList.size(); i++) {
            String thisId = this.contactList.elementAt(i).getId();
            if (thisId.equals(contactId)) {
                return this.contactList.elementAt(i);
            }
        }
        throw new NullPointerException("Contact ID not found");
    }

    /**
     * Remove a contact from the collection
     * @param contactId the ID string of the contact to remove
     * @throws NullPointerException if the contact does not exist
     */
    public void deleteContact(String contactId) throws NullPointerException{
        for (int i = 0; i < this.contactList.size(); i++) {
            String thisId = this.contactList.elementAt(i).getId();
            if (thisId.equals(contactId)) {
                this.contactList.remove(i);
                return;
            }
        }
        throw new NullPointerException("Contact ID not found");
    }

    /**
     * Update the specified field of a contact with the specified value
     *
     * @param contactId the ID string of the contact to update
     * @param field the contact field to update
     * @param value the new value for the updated field
     * @throws NullPointerException if the contact does not exist
     */
    public void updateContact(String contactId, Contact.UpdateableField field, String value)
        throws IllegalArgumentException, NullPointerException {
        for (int i = 0; i < this.contactList.size(); i++) {
            String thisId = this.contactList.elementAt(i).getId();
            if (thisId.equals(contactId)) {
                switch (field) {
                case FIRST_NAME:
                    this.contactList.elementAt(i).setFirstName(value);
                    return;
                case LAST_NAME:
                    this.contactList.elementAt(i).setLastName(value);
                    return;
                case PHONE:
                    this.contactList.elementAt(i).setPhone(value);
                    return;
                case ADDRESS:
                    this.contactList.elementAt(i).setAddress(value);
                    return;
                }
            }
        }
        throw new NullPointerException("Contact ID not found");
    }
}
