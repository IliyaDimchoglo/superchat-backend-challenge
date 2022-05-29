package com.example.superchat.service;

import com.example.superchat.dto.MessageDto;
import com.example.superchat.entity.Channel;
import com.example.superchat.entity.Contact;
import com.example.superchat.entity.Message;
import com.example.superchat.entity.enums.ChannelType;
import com.example.superchat.exception.RestResponseException;
import com.example.superchat.mapper.MessageMapper;
import com.example.superchat.service.db.ChannelDbService;
import com.example.superchat.service.db.ContactDbService;
import com.example.superchat.service.db.MessageDbService;
import com.example.superchat.service.impl.MessageServiceImpl;
import com.example.superchat.service.strategy.manager.MessageSenderManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageServiceTest {

    @Mock
    private MessageMapper messageMapper;
    @Mock
    private ContactDbService contactDbService;
    @Mock
    private ChannelDbService channelDbService;
    @Mock
    private MessageDbService messageDbService;
    @Mock
    private MessageSenderManager messageManager;
    @Mock
    private TextTransformationService textTransformationService;

    @InjectMocks
    private MessageServiceImpl messageService;


    @Test
    void send_message_return_success() {
        //given
        var email = "email@gmail.com";
        var name = "name";
        var text = "text";
        var messageDto = new MessageDto(name, email, ChannelType.INTERNAL.toString(), text);
        var contact = new Contact(name, email);
        var channel = new Channel(contact, ChannelType.INTERNAL);

        when(contactDbService.getContactByEmail(email)).thenReturn(contact);
        when(channelDbService.getChannelByContactAndTypeOrCreateNew(contact, ChannelType.INTERNAL)).thenReturn(channel);
        when(messageDbService.save(any())).thenReturn(new Message());
        when(textTransformationService.transform(text, name, email)).thenReturn(text);

        //when
        //then
        assertDoesNotThrow(() -> messageService.sendMessage(messageDto));
    }

    @Test
    void send_message_where_contact_not_exists_return_exception() {
        //given
        var email = "email@gmail.com";
        var name = "name";
        var text = "text";
        var messageDto = new MessageDto(name, email, ChannelType.INTERNAL.toString(), text);

        when(contactDbService.getContactByEmail(email)).thenThrow(new RestResponseException(HttpStatus.NOT_FOUND, "Contact not found"));

        try {
            //when
            messageService.sendMessage(messageDto);
        } catch (Exception ex) {
            //then
            assertEquals(ex.getClass(), RestResponseException.class);
            assertEquals("Contact not found", ex.getMessage());
        }
    }

    @Test
    void get_all_messages_by_contact_return_list_of_messages() {
        //given
        var email = "email@gmail.com";
        var name = "name";
        var text = "text";
        var contact = new Contact(name, email);
        var channel = new Channel(contact, ChannelType.INTERNAL);
        var message = new Message(contact, channel, text);
        var messageDto = new MessageDto(name, email, ChannelType.INTERNAL.toString(), text);

        when(messageDbService.findAllByContactEmail(email)).thenReturn(Collections.singletonList(message));
        when(messageMapper.toDto(message)).thenReturn(messageDto);


        //when
        List<MessageDto> messageDtos = messageService.getAllByEmail(email);

        //then
        assertEquals("INTERNAL", messageDtos.get(0).getChannelType());
        assertEquals(name, messageDtos.get(0).getName());
        assertEquals(email, messageDtos.get(0).getEmail());
        assertEquals(text, messageDtos.get(0).getText());
    }
}
