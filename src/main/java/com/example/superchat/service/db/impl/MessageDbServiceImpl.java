package com.example.superchat.service.db.impl;

import com.example.superchat.entity.Message;
import com.example.superchat.repository.MessageRepository;
import com.example.superchat.service.db.MessageDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageDbServiceImpl implements MessageDbService {

    private final MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> findAllByContactEmail(String email) {
        return messageRepository.findAllByContactEmail(email);
    }
}
