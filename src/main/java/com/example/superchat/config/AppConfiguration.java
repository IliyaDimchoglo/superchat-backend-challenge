package com.example.superchat.config;

import org.apache.commons.text.StringSubstitutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class AppConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        var restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    @Bean
    public StringSubstitutor stringSubstitutor(){
        StringSubstitutor stringSubstitutor = new StringSubstitutor();
        stringSubstitutor.setEnableUndefinedVariableException(true);
        return stringSubstitutor;
    }
}
