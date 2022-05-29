package com.example.superchat.service.strategy.impl;

import com.example.superchat.entity.enums.ChannelType;
import com.example.superchat.service.strategy.MessageStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WhatsAppMessageStrategy implements MessageStrategy {

    @Override
    public void sendMessage(String text, String email) {
        //send somewhere logic...
        log.info("[x] WhatsApp Message sent successfully. Contact: {}, Channel type: {}", email, getType());
    }

    @Override
    public ChannelType getType() {
        return ChannelType.WHATS_APP;
    }
}
