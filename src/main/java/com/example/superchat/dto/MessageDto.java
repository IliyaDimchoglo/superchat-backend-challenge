package com.example.superchat.dto;

import com.example.superchat.entity.enums.ChannelType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    @NotBlank(message = "Add name")
    private String name;

    @Email(message = "Add email")
    private String email;

    @NotNull(message = "Add channel type")
    private ChannelType channelType;

    @NotBlank(message = "Add text")
    private String text;
}
