package com.simsasookbak.alan.service;

import com.simsasookbak.alan.dto.AlanResponseDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AlanService {

    private static final String URL = "https://kdt-api-function.azurewebsites.net/api/v1/question";

    private static final String CLIENT_ID = "980f05cc-e7c9-433b-9581-8a5b2837d4e0";
    private final RestTemplate restTemplate;

    public AlanService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public AlanResponseDto getAlan(String content) {
        String uri = UriComponentsBuilder
                .fromHttpUrl(URL)
                .queryParam("content", content)
                .queryParam("client_id", CLIENT_ID)
                .toUriString();

        return restTemplate.getForObject(uri, AlanResponseDto.class);
    }
}
