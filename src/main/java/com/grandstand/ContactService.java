package com.grandstand;

import java.util.Vector;
import com.grandstand.Contact;

public class ContactService {
    private Vector<Contact> contactList;
    private int idCounter;

    public ContactService() {
        this.contactList = new Vector<Contact>();
        this.idCounter = 1;
    }

    public void addContact(String firstName, String lastName,
                           String phone, String Address) {

        // Generate a new contact ID from the ID counter
        String contactId = String.format("%010d", this.idCounter);

        // Assemble the new Contact
        Contact newContact = new Contact(contactId);
        newContact.setFirstName(firstName);
        newContact.setLastName(lastName);
        newContact.setPhone(phone);
        newContact.setAddress(Address);

        // Add the new contact to the list
        this.contactList.add(newContact);
        this.idCounter++;
    }

    public Contact getContactById(String contactId) {
        for (int i = 0; i < this.contactList.size(); i++) {
            String thisId = this.contactList.elementAt(i).getId();
            if (thisId.equals(contactId)) {
                return this.contactList.elementAt(i);
            }
        }
        throw new NullPointerException("Contact ID not found");
    }

    public void deleteContact(String contactId) {
        for (int i = 0; i < this.contactList.size(); i++) {
            String thisId = this.contactList.elementAt(i).getId();
            if (thisId.equals(contactId)) {
                this.contactList.remove(i);
                return;
            }
        }
        throw new NullPointerException("Contact ID not found");
    }

    public void updateContact(String contactId, String field, String value) {
        for (int i = 0; i < this.contactList.size(); i++) {
            String thisId = this.contactList.elementAt(i).getId();
            if (thisId.equals(contactId)) {
                switch (field) {
                case "firstName":
                    this.contactList.elementAt(i).setFirstName(value);
                    return;
                case "lastName":
                    this.contactList.elementAt(i).setLastName(value);
                    return;
                case "phone":
                    this.contactList.elementAt(i).setPhone(value);
                    return;
                case "address":
                    this.contactList.elementAt(i).setAddress(value);
                    return;
                default:
                    String message = String.format("Cannot update field \"%s\"", value);
                    throw new IllegalArgumentException(message);
                }
            }
        }
        throw new NullPointerException("Contact ID not found");
    }
}
