package com.example.superchat.util.template;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TemplateEngine {

    public static String of(String text, String name, String email, String bitcoinRate, String bitcoinCurrency) {
        try {
            var substitutes = putTemplateVariables(name, email, bitcoinRate, bitcoinCurrency);
            var stringSubstitute = new StringSubstitutor(substitutes).setEnableUndefinedVariableException(true);
            return stringSubstitute.replace(text);
        } catch (Exception e) {
            log.error("Failed to update text variables. Message {}", e.getMessage());
            return text;
        }
    }

    private static Map<String, String> putTemplateVariables(String name, String email, String bitcoinRate, String bitcoinCurrency) {
        Map<String, String> substitutes = new HashMap<>();
        substitutes.put("name", name);
        substitutes.put("email", email);
        substitutes.put("BitCoin", bitcoinRate + "/" + bitcoinCurrency);
        return substitutes;
    }
}
