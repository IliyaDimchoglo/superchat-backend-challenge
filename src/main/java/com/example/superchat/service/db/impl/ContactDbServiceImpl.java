package com.example.superchat.service.db.impl;

import com.example.superchat.entity.Contact;
import com.example.superchat.repository.ContactRepository;
import com.example.superchat.service.db.ContactDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactDbServiceImpl implements ContactDbService {

    private final ContactRepository contactRepository;

    @Override
    public Optional<Contact> findContactByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public boolean isExists(String email) {
        return contactRepository.existsByEmail(email);
    }

    @Override
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }
}
