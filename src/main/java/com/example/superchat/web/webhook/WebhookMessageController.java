package com.example.superchat.web.webhook;

import com.example.superchat.dto.MessageDto;
import com.example.superchat.service.WebhookMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/webhook/messages")
@RequiredArgsConstructor
public class WebhookMessageController {

    private final WebhookMessageService whatsAppMessageService;

    @PostMapping
    public void receiveMessage(@Valid @RequestBody MessageDto messageDto) {
        whatsAppMessageService.receiveMessage(messageDto);
    }
}
