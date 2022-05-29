package com.example.superchat.service;

import com.example.superchat.dto.ContactDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContactService {

    @Transactional
    void create(ContactDto request);

    @Transactional(readOnly = true)
    List<ContactDto> getAll();
}
