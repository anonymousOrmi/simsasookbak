package com.simsasookbak.member.controller;


import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.RegisterDto;
import com.simsasookbak.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.http.HttpResponse;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping(value = "/member/register")
    public ResponseEntity<String> regist(@ModelAttribute RegisterDto member){
        try{
            memberService.register(member.toEntity());
            return ResponseEntity.ok().body(member.toString());
        }catch (IllegalArgumentException | DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


    /* *
    * 페이지
    *  */
    @GetMapping("/register")
    public String registerPage(){
        return "register";
    }
}
