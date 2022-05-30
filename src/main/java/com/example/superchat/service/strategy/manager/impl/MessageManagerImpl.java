package com.example.superchat.service.strategy.manager.impl;

import com.example.superchat.entity.enums.ChannelType;
import com.example.superchat.service.strategy.MessageStrategy;
import com.example.superchat.service.strategy.impl.InternalMessageStrategy;
import com.example.superchat.service.strategy.manager.MessageSenderManager;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

@Component
public class MessageManagerImpl implements MessageSenderManager {

    private final Map<ChannelType, MessageStrategy> map;

    public MessageManagerImpl(List<MessageStrategy> map) {
        this.map = map.stream().collect(toMap(MessageStrategy::getType, Function.identity()));
    }

    @Override
    public void execute(ChannelType channelType, String text, String email) {
        map.getOrDefault(channelType, new InternalMessageStrategy()).sendMessage(text, email);
    }
}
