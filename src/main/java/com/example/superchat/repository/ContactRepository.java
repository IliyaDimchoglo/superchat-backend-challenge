package com.example.superchat.repository;

import com.example.superchat.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {

    boolean existsByEmail(String email);

    Optional<Contact> findByEmail(String email);
}
