package ru.academits.smolenskaya.dao;

import ru.academits.smolenskaya.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Anna on 17.06.2017.
 */
public class ContactDao {
    private List<Contact> contactList = new ArrayList<>();
    private AtomicInteger idSequence = new AtomicInteger(0);

    public ContactDao() {
        Contact contact = new Contact();
        contact.setId(getNewId());
        contact.setFirstName("Иван");
        contact.setLastName("Иванов");
        contact.setPhone("9123456789");
        contactList.add(contact);
    }

    private int getNewId() {
        return idSequence.addAndGet(1);
    }

    public List<Contact> getAllContacts() {
        return contactList;
    }

    public void add(Contact contact) {
        contact.setId(getNewId());
        contactList.add(contact);
    }

    public int delete(String[] phones) {
        int deletedContactsCount = 0;

        for (String phone : phones) {
            for (int j = 0; j < contactList.size(); j++) {
                if (Objects.equals(contactList.get(j).getPhone(), phone)) {
                    contactList.remove(j);
                    deletedContactsCount++;

                    break;
                }
            }
        }

        return deletedContactsCount;
    }
}
