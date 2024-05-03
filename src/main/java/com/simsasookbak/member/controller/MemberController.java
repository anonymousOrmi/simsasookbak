package com.simsasookbak.member.controller;


import com.simsasookbak.member.domain.AddUserDto;
import com.simsasookbak.member.domain.RegisterDto;
import com.simsasookbak.member.service.MemberService;
import com.simsasookbak.member.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    private final UserDetailService userDetailService;

    @PostMapping(value = "/member/register")
    public ResponseEntity<String> regist(@ModelAttribute RegisterDto member){
        try{

            memberService.register(member.toEntity());
            log.error("ㅁㅁ {}",member);
            return ResponseEntity.ok().body(member.toString());

        }catch (IllegalArgumentException | DataIntegrityViolationException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping(value = "/member/login")
    public String signin(@RequestBody AddUserDto addUserDto, Model model){
        userDetailService.loadUserByUsername(addUserDto.getEmail());
//        (addUserDto.getEmail(),
//                addUserDto.getPassword())
        model.addAttribute("email",addUserDto.getEmail());
        return "index";
    }

//    @PostMapping(value = "/login")
//    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password){
//        memberService.loadUserByUsername(email);
//    }


    /* *
    * 페이지
    *  */
    @GetMapping("/login")
    public String registerPage(){
        return "login";
    }
}
