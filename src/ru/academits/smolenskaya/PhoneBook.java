package ru.academits.smolenskaya;

import ru.academits.smolenskaya.converter.ContactConverter;
import ru.academits.smolenskaya.converter.ContactValidationConverter;
import ru.academits.smolenskaya.dao.ContactDao;
import ru.academits.smolenskaya.service.ContactService;

/**
 * Created by Anna on 14.06.2017.
 */
public class PhoneBook {

    public static ContactDao contactDao = new ContactDao();

    public static ContactService phoneBookService = new ContactService();

    public static ContactConverter contactConverter = new ContactConverter();

    public static ContactValidationConverter contactValidationConverter = new ContactValidationConverter();
}
