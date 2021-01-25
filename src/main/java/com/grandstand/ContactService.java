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
}
