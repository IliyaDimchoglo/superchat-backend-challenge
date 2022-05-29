package com.example.superchat.service.db.impl;

import com.example.superchat.entity.Channel;
import com.example.superchat.entity.Contact;
import com.example.superchat.entity.enums.ChannelType;
import com.example.superchat.repository.ChannelRepository;
import com.example.superchat.service.db.ChannelDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChannelDbServiceImpl implements ChannelDbService {

    private final ChannelRepository channelRepository;

    @Override
    public Optional<Channel> findChannelById(UUID channelId) {
        return channelRepository.findById(channelId);
    }

    @Override
    public Channel save(Channel channel) {
        return channelRepository.save(channel);
    }

    @Override
    public Optional<Channel> findByContactAndType(Contact contact, ChannelType channelType) {
        return channelRepository.findByContactAndType(contact, channelType);
    }
}
