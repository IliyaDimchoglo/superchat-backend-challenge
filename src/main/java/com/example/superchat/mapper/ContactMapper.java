package com.example.superchat.mapper;

import com.example.superchat.dto.ContactDto;
import com.example.superchat.entity.Contact;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    ContactDto toDto(Contact contact);
}
