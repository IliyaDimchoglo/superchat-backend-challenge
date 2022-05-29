package com.example.superchat.entity.enums;

import com.example.superchat.exception.RestResponseException;
import org.springframework.http.HttpStatus;

import java.util.stream.Stream;

public enum ChannelType {
    INTERNAL, GOOGLE, WHATS_APP;

    public static ChannelType getEnumTypeByString(String type) {
        return Stream.of(ChannelType.values())
                .filter(channelType -> channelType.toString().equals(type.toUpperCase()))
                .findFirst()
                .orElseThrow(() -> new RestResponseException(HttpStatus.CONFLICT, "Channel type not found"));
    }
}
