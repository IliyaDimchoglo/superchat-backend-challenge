package com.example.superchat.web;

import com.example.superchat.dto.ContactDto;
import com.example.superchat.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping
    public void create(@Valid @RequestBody ContactDto request) {
        contactService.create(request);
    }

    @GetMapping
    public List<ContactDto> getAll() {
        return contactService.getAll();
    }
}
