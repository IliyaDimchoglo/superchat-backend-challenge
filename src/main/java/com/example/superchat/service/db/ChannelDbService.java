package com.example.superchat.service.db;

import com.example.superchat.entity.Channel;
import com.example.superchat.entity.Contact;
import com.example.superchat.entity.enums.ChannelType;
import com.example.superchat.exception.RestResponseException;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

public interface ChannelDbService {

    Optional<Channel> findChannelById(UUID channelId);

    Channel save(Channel channel);

    default Channel getChannelById(UUID channelId) throws RestResponseException {
        return findChannelById(channelId).orElseThrow(() -> new RestResponseException(HttpStatus.NOT_FOUND, "Channel not found"));
    }

    Optional<Channel> findByContactAndType(Contact contact, ChannelType channelType);

    default Channel getChannelByContactAndTypeOrCreateNew(Contact contact, ChannelType channelType) {
        return findByContactAndType(contact, channelType).orElseGet(() -> save(new Channel(contact, channelType)));
    }
}
