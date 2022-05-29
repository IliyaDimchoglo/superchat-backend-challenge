package com.example.superchat.service.impl;

import com.example.superchat.dto.MessageDto;
import com.example.superchat.entity.Message;
import com.example.superchat.entity.enums.ChannelType;
import com.example.superchat.mapper.MessageMapper;
import com.example.superchat.service.MessageService;
import com.example.superchat.service.TextTransformationService;
import com.example.superchat.service.db.ChannelDbService;
import com.example.superchat.service.db.ContactDbService;
import com.example.superchat.service.db.MessageDbService;
import com.example.superchat.service.strategy.manager.MessageSenderManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.superchat.entity.enums.ChannelType.getEnumTypeByString;

@Slf4j
@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageMapper messageMapper;
    private final ContactDbService contactDbService;
    private final ChannelDbService channelDbService;
    private final MessageDbService messageDbService;
    private final MessageSenderManager messageManager;
    private final TextTransformationService textTransformationService;

    @Override
    public void sendMessage(MessageDto messageDto) {
        var contact = contactDbService.getContactByEmail(messageDto.getEmail());
        var channel = channelDbService.getChannelByContactAndTypeOrCreateNew(contact, getEnumTypeByString(messageDto.getChannelType()));
        var transformedText = textTransformationService.transform(messageDto.getText(), contact.getName(), contact.getEmail());
        messageDbService.save(new Message(contact, channel, transformedText));

        messageManager.execute(channel.getType(), transformedText, contact.getEmail());
    }

    @Override
    public List<MessageDto> getAllByEmail(String email) {
        return messageDbService.findAllByContactEmail(email)
                .stream()
                .map(messageMapper::toDto)
                .collect(Collectors.toList());
    }
}
