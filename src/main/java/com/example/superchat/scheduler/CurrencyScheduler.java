package com.example.superchat.scheduler;

import com.example.superchat.entity.BitcoinRate;
import com.example.superchat.repository.BitcoinRateRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class CurrencyScheduler {

    private final RestTemplate restTemplate;
    private final BitcoinRateRepository bitcoinRateRepository;

    private static final String URL = "https://api.coindesk.com/v1/bpi/currentprice.json";

    @Scheduled(fixedDelay = 600000)
    @Transactional
    public void saveBitCoinRate() {
        try {
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(URL, JsonNode.class);
            var code = readResponseTree(response, "code");
            var rate = readResponseTree(response, "rate");
            var bitCoinRate = bitcoinRateRepository.save(new BitcoinRate(code, rate));
            log.info("[x] Bitcoin rate successfully updated, rate {}, currency {}", bitCoinRate.getRate(), bitCoinRate.getCurrency());
        } catch (Exception e) {
            log.error("[x] Bitcoin update failed. Message: {}", e.getMessage());
        }
    }

    private String readResponseTree(ResponseEntity<JsonNode> response, String key) {
        return response.getBody().path("bpi").path("USD").path(key).textValue();
    }
}
