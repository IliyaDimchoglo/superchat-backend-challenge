package com.example.superchat.service.db;

import com.example.superchat.entity.Message;

import java.util.List;

public interface MessageDbService {

    Message save(Message message);

    List<Message> findAllByContactEmail(String email);
}
