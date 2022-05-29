package com.example.superchat.dto;

import com.example.superchat.util.validator.ChannelTypeConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    @NotBlank(message = "Add name")
    private String name;

    @Email(message = "Add email")
    private String email;

    @ChannelTypeConstraint
    private String channelType;

    @NotBlank(message = "Add text")
    private String text;
}
