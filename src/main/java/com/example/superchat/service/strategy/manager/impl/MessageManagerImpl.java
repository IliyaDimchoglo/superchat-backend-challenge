package com.example.superchat.service.strategy.manager.impl;

import com.example.superchat.entity.enums.ChannelType;
import com.example.superchat.exception.RestResponseException;
import com.example.superchat.service.strategy.MessageStrategy;
import com.example.superchat.service.strategy.manager.MessageSenderManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Component
public class MessageManagerImpl implements MessageSenderManager {

    private final Map<ChannelType, MessageStrategy> map;

    public MessageManagerImpl(List<MessageStrategy> map) {
        this.map = map.stream().collect(toMap(MessageStrategy::getType, Function.identity()));
    }

    @Override
    public void execute(ChannelType channelType, String text, String email) {
        try {
            map.get(channelType).sendMessage(text, email);
        } catch (Exception e) {
            log.error("Message manager can't handle Channel type:{}, ex: {}", channelType, e.getMessage());
            throw new RestResponseException(HttpStatus.NOT_FOUND, "Channel type no supported");
        }
    }
}
