package com.example.superchat.util.validator;

import com.example.superchat.entity.enums.ChannelType;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;
import java.util.stream.Stream;

class ChannelTypeValidator implements ConstraintValidator<ChannelTypeConstraint, String> {

    @Override
    public void initialize(ChannelTypeConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String type, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.nonNull(type) && Stream.of(ChannelType.values()).anyMatch(channelType -> channelType.toString().equals(type.toUpperCase()));
    }
}
