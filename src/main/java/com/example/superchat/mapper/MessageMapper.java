package com.example.superchat.mapper;

import com.example.superchat.dto.MessageDto;
import com.example.superchat.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "message.contact.name", target = "name")
    @Mapping(source = "message.contact.email", target = "email")
    @Mapping(source = "channel.type", target = "channelType")
    MessageDto toDto(Message message);
}
