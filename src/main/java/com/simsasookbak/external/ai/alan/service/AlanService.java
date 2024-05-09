package com.simsasookbak.external.ai.alan.service;

import com.simsasookbak.external.ai.alan.dto.AlanResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class AlanService {

    @Value("${ALAN_ONE_URL}")
    private String URL;

    @Value("${CLIENT_ID}")
    private String CLIENT_ID;

    private final RestTemplate restTemplate;


    public AlanResponseDto getAlan(String content) {
        String uri = UriComponentsBuilder
                .fromHttpUrl(URL)
                .queryParam("content", content)
                .queryParam("client_id", CLIENT_ID)
                .toUriString();

        return restTemplate.getForObject(uri, AlanResponseDto.class);
    }
}
