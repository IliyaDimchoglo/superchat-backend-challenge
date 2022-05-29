package com.example.superchat.web;

import com.example.superchat.dto.MessageDto;
import com.example.superchat.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public void sendMessage(@Valid @RequestBody MessageDto messageDto) {
        messageService.sendMessage(messageDto);
    }

    @Validated
    @GetMapping("/{email}")
    public List<MessageDto> getAllById(@Email @PathVariable String email) {
        return messageService.getAllByEmail(email);
    }
}
