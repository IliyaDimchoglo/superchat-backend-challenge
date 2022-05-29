package com.example.superchat.service;

import com.example.superchat.dto.MessageDto;
import com.example.superchat.entity.Channel;
import com.example.superchat.entity.Contact;
import com.example.superchat.entity.Message;
import com.example.superchat.entity.enums.ChannelType;
import com.example.superchat.service.db.ChannelDbService;
import com.example.superchat.service.db.ContactDbService;
import com.example.superchat.service.db.MessageDbService;
import com.example.superchat.service.impl.WebhookMessageServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WebhookMessageServiceTest {

    @Mock
    private ContactDbService contactDbService;
    @Mock
    private ChannelDbService channelDbService;
    @Mock
    private MessageDbService messageDbService;
    @Mock
    private TextTransformationService textTransformationService;

    @InjectMocks
    private WebhookMessageServiceImpl webhookMessageService;

    @ParameterizedTest
    @MethodSource("getArguments")
    void receive_message_return_success(Optional<Contact> contact) {
        //given
        var email = "email@gmail.com";
        var name = "name";
        var text = "text";
        var messageDto = new MessageDto(name, email, ChannelType.WHATS_APP, text);

        when(contactDbService.findContactByEmail(email)).thenReturn(contact);
        when(channelDbService.save(any())).thenReturn(new Channel());
        when(messageDbService.save(any())).thenReturn(new Message());
        when(textTransformationService.transform(text, name, email)).thenReturn(text);

        //when
        //then
        assertDoesNotThrow(() -> webhookMessageService.receiveMessage(messageDto));
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(Arguments.of(Optional.empty()), Arguments.of(Optional.of(new Contact("name", "email@gmail.com"))));
    }
}
