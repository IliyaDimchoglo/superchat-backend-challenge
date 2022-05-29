package com.example.superchat.service.impl;

import com.example.superchat.dto.ContactDto;
import com.example.superchat.mapper.ContactMapper;
import com.example.superchat.service.ContactService;
import com.example.superchat.service.db.ContactDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactDbService contactDbService;
    private final ContactMapper contactMapper;

    @Override
    public void create(ContactDto request) {
        contactDbService.saveIfNotExists(request.getName(), request.getEmail());
        log.info("[x] Contact successfully saved. Name: {}, Email: {}", request.getName(), request.getEmail());
    }

    @Override
    public List<ContactDto> getAll() {
        return contactDbService.getAll().stream()
                .map(contactMapper::toDto)
                .collect(Collectors.toList());
    }
}
