package com.simsasookbak.email.controller;

import com.simsasookbak.email.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/email")
public class MailController {
    private final MailService mailService;

    @PostMapping
    public ResponseEntity<Void> sendMail() throws Exception {
        //  TODO 매개 변수 DTO
        // TODO dto 생성 전 까지 하드코딩
        mailService.send();
        return ResponseEntity.ok().build();
    }
}
