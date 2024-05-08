package com.simsasookbak.external.ai.alan.controller;

import com.simsasookbak.external.ai.alan.dto.AlanResponseDto;
import com.simsasookbak.external.ai.alan.service.AlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/alan")
public class AlanController {
    private final AlanService alanService;

    @GetMapping("/simpleAnswer")
    public ResponseEntity<AlanResponseDto> getAlanResponse(@RequestParam String content) {
        AlanResponseDto response = alanService.getAlan(content);
        return ResponseEntity.ok(response);
    }
}
