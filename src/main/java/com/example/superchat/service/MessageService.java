package com.example.superchat.service;

import com.example.superchat.dto.MessageDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MessageService {

    @Transactional
    void sendMessage(MessageDto messageDto);

    @Transactional(readOnly = true)
    List<MessageDto> getAllByEmail(String email);
}
