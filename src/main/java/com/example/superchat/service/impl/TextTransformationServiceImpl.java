package com.example.superchat.service.impl;

import com.example.superchat.repository.BitcoinRateRepository;
import com.example.superchat.service.TextTransformationService;
import com.example.superchat.util.template.TemplateEngine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TextTransformationServiceImpl implements TextTransformationService {

    private final BitcoinRateRepository bitcoinRateRepository;

    @Override
    public String transform(String text, String name, String email) {
        return bitcoinRateRepository.findFirstByOrderByCreatedTimeAsc()
                .map(bitcoinRate -> TemplateEngine.of(text, name, email, bitcoinRate.getRate(), bitcoinRate.getCurrency()))
                .orElseGet(() -> {
                    log.error("[x] Something went wrong. Bitcoin rate not suppoerted.");
                    return TemplateEngine.of(text, name, email, "Currently not supported", "");
                });
    }
}
