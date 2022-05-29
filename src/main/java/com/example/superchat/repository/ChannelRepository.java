package com.example.superchat.repository;

import com.example.superchat.entity.Channel;
import com.example.superchat.entity.Contact;
import com.example.superchat.entity.enums.ChannelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ChannelRepository extends JpaRepository<Channel, UUID> {

    Optional<Channel> findByContactAndType(Contact contact, ChannelType channelType);
}
