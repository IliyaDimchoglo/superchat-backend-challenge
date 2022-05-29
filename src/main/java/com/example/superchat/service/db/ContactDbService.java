package com.example.superchat.service.db;

import com.example.superchat.entity.Contact;
import com.example.superchat.exception.RestResponseException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface ContactDbService {

    default void saveIfNotExists(String name, String email) throws RestResponseException {
        if (isExists(email)) {
            throw new RestResponseException(HttpStatus.CONFLICT, "Contact already exists.");
        } else save(new Contact(name, email));
    }

    default Contact getContactByEmail(String email) throws RestResponseException {
        return findContactByEmail(email)
                .orElseThrow(() -> new RestResponseException(HttpStatus.NOT_FOUND, "Contact not found"));
    }

    Optional<Contact> findContactByEmail(String email);

    Contact save(Contact contact);

    boolean isExists(String email);

    List<Contact> getAll();
}
