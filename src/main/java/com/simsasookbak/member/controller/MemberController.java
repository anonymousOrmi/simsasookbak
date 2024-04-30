package com.simsasookbak.member.controller;


import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpResponse;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping(value = "/member/register")
    public ResponseEntity<String> regist(Member member){
        try{
            memberService.register(member);
            return ResponseEntity.ok().body(member.toString());
        }catch (IllegalArgumentException | DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
