package com.example.superchat.service.strategy.manager;

import com.example.superchat.entity.enums.ChannelType;

public interface MessageSenderManager {

    void execute(ChannelType channelType, String text, String email);
}
