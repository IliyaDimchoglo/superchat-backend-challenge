package com.example.superchat.service;

import com.example.superchat.dto.MessageDto;
import org.springframework.transaction.annotation.Transactional;

public interface WebhookMessageService {

    @Transactional
    void receiveMessage(MessageDto messageDto);
}
