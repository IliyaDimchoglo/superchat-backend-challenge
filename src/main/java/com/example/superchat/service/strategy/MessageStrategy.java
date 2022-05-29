package com.example.superchat.service.strategy;

import com.example.superchat.entity.enums.ChannelType;

public interface MessageStrategy {

    void sendMessage(String text, String email);

    ChannelType getType();
}
