package com.simsasookbak.alan.controller;

import com.simsasookbak.alan.dto.AlanResponseDto;
import com.simsasookbak.alan.service.AlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlanController {
    private final AlanService alanService;

    @GetMapping("/alan")
    public ResponseEntity<AlanResponseDto> getAlanResponse(@RequestParam String content) {
        AlanResponseDto response = alanService.getAlan(content);
        return ResponseEntity.ok(response);
    }
}
