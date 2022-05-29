package com.example.superchat.mapper;

import com.example.superchat.dto.MessageDto;
import com.example.superchat.entity.Message;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageDto toDto(Message message);
}
