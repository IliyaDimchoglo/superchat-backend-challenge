package com.example.superchat.service.impl;

import com.example.superchat.dto.MessageDto;
import com.example.superchat.entity.Channel;
import com.example.superchat.entity.Contact;
import com.example.superchat.entity.Message;
import com.example.superchat.entity.enums.ChannelType;
import com.example.superchat.service.TextTransformationService;
import com.example.superchat.service.WebhookMessageService;
import com.example.superchat.service.db.ChannelDbService;
import com.example.superchat.service.db.ContactDbService;
import com.example.superchat.service.db.MessageDbService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.superchat.entity.enums.ChannelType.getEnumTypeByString;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebhookMessageServiceImpl implements WebhookMessageService {

    private final ContactDbService contactDbService;
    private final ChannelDbService channelDbService;
    private final MessageDbService messageDbService;
    private final TextTransformationService textTransformationService;

    @Override
    public void receiveMessage(MessageDto messageDto) {
        var channelType = getEnumTypeByString(messageDto.getChannelType());
        var channel = contactDbService.findContactByEmail(messageDto.getEmail())
                .map(contact ->
                        channelDbService.getChannelByContactAndTypeOrCreateNew(contact, channelType))
                .orElseGet(() -> {
                    var contact = contactDbService.save(new Contact(messageDto.getName(), messageDto.getEmail()));
                    return channelDbService.save(new Channel(contact, channelType));
                });

        var transformedText = textTransformationService.transform(messageDto.getText(), messageDto.getName(), messageDto.getEmail());
        messageDbService.save(new Message(channel.getContact(), channel, transformedText));
        log.info("Webhook message successfully received. Channel type {}. email {}", messageDto.getChannelType(), messageDto.getEmail());
    }
}
