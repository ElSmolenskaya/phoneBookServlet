package ru.academits.smolenskaya.servlet;

import ru.academits.smolenskaya.PhoneBook;
import ru.academits.smolenskaya.service.ContactService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class DeleteContactsServlet extends HttpServlet {
    private final ContactService phoneBookService = PhoneBook.phoneBookService;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try (OutputStream responseStream = resp.getOutputStream()) {
            String phonesLine = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String[] phones = phonesLine.split(",");

            String deletedContactsInformation = phoneBookService.deleteContacts(phones);

            responseStream.write(deletedContactsInformation.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println("error in DeleteContactsServlet GET: ");
            e.printStackTrace();
        }
    }
}