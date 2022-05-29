package com.example.superchat.service;

import com.example.superchat.dto.ContactDto;
import com.example.superchat.entity.Contact;
import com.example.superchat.exception.RestResponseException;
import com.example.superchat.mapper.ContactMapper;
import com.example.superchat.service.db.ContactDbService;
import com.example.superchat.service.impl.ContactServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @Mock
    private ContactDbService contactDbService;
    @Mock
    private ContactMapper contactMapper;

    @InjectMocks
    private ContactServiceImpl contactService;

    @Test
    void create_contact_return_success() {
        //given
        var name = "name";
        var email = "email@gmail.com";
        var contactDto = new ContactDto(name, email);

        doNothing().when(contactDbService).saveIfNotExists(name, email);

        //when
        //then
        assertDoesNotThrow(() -> contactService.create(contactDto));
    }

    @Test
    void create_contact_where_contact_already_exists_return_exception() {
        //given
        var name = "name";
        var email = "email@gmail.com";
        var contactDto = new ContactDto(name, email);

        try {
            //when
            contactService.create(contactDto);
        } catch (Exception ex) {
            //then
            assertEquals(ex.getClass(), RestResponseException.class);
            assertEquals("Contact already exists.", ex.getMessage());
        }
    }

    @Test
    void get_all_contacts_return_list_of_contact_dtos() {
        //given
        var name = "name";
        var email = "email@gmail.com";
        var contact = new Contact(name, email);
        var contactDto = new ContactDto(name, email);

        when(contactDbService.getAll()).thenReturn(Collections.singletonList(contact));
        when(contactMapper.toDto(contact)).thenReturn(contactDto);

        //when
        List<ContactDto> contactDtos = contactService.getAll();

        //then
        verify(contactDbService, times(1)).getAll();
        assertEquals(email, contactDtos.get(0).getEmail());
        assertEquals(name, contactDtos.get(0).getName());
    }
}
