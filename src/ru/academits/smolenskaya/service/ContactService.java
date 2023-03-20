package ru.academits.smolenskaya.service;

import ru.academits.smolenskaya.PhoneBook;
import ru.academits.smolenskaya.dao.ContactDao;
import ru.academits.smolenskaya.model.Contact;

import java.util.List;


public class ContactService {
    private ContactDao contactDao = PhoneBook.contactDao;

    private boolean isExistContactWithPhone(String phone) {
        List<Contact> contactList = contactDao.getAllContacts();
        for (Contact contact : contactList) {
            if (contact.getPhone().equals(phone)) {
                return true;
            }
        }
        return false;
    }

    public ContactValidation validateContact(Contact contact) {
        ContactValidation contactValidation = new ContactValidation();
        contactValidation.setValid(true);
        if (contact.getFirstName().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Имя должно быть заполнено.");
            return contactValidation;
        }

        if (contact.getLastName().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Фамилия должно быть заполнено.");
            return contactValidation;
        }

        if (contact.getPhone().isEmpty()) {
            contactValidation.setValid(false);
            contactValidation.setError("Поле Телефон должно быть заполнено.");
            return contactValidation;
        }

        if (isExistContactWithPhone(contact.getPhone())) {
            contactValidation.setValid(false);
            contactValidation.setError("Номер телефона не должен дублировать другие номера в телефонной книге.");
            return contactValidation;
        }
        return contactValidation;
    }

    public ContactValidation addContact(Contact contact) {
        ContactValidation contactValidation = validateContact(contact);
        if (contactValidation.isValid()) {
            contactDao.add(contact);
        }
        return contactValidation;
    }

    public String deleteContacts(String[] phones) {
        int deletedContactsCount = contactDao.delete(phones);

        return deletedContactsCount > 0
                ? "Количество удаленных контактов: " + deletedContactsCount
                : "Ни один контакт не был удален";
    }

    public List<Contact> getAllContacts() {
        return contactDao.getAllContacts();
    }
}
