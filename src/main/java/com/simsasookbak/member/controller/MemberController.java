package com.simsasookbak.member.controller;


import com.simsasookbak.member.domain.AddUserDto;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.RegisterDto;
import com.simsasookbak.member.service.MemberService;
import com.simsasookbak.member.service.UserDetailService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    @PostMapping(value = "/member/register")
    public String regist(@RequestBody RegisterDto member){
        try{

            memberService.register(member.toEntity());
            log.error("ㅁㅁ {}",member);
//            return ResponseEntity.ok().body(member.toString());
            return "redirect:/login";

        }catch (IllegalArgumentException | DataIntegrityViolationException ex){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            return "/";
        }
    }

//    @PostMapping(value = "/member/login")
//    public String signin(@RequestBody AddUserDto addUserDto, Model model){
//        userDetailService.loadUserByUsername(addUserDto.getEmail());
////        (addUserDto.getEmail(),
////                addUserDto.getPassword())
//        model.addAttribute("email",addUserDto.getEmail());
//        return "index";
//    }

//    @PostMapping(value = "/login")
//    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password){
//        memberService.loadUserByUsername(email);
//    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler()
                .logout(request, response, SecurityContextHolder.getContext()
                .getAuthentication());
        return "redirect:/login";
    }
    @ResponseBody
    @GetMapping("/t")
    public String t(@RequestParam String msg){
        return msg;
    }
    /* *
    * 페이지
    *  */
    @GetMapping("/login")
    public String registerPage(){
        return "login";
    }



    @GetMapping("/userinfo")
    @ResponseBody
    public ResponseEntity<String> getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member user = (Member)authentication.getPrincipal();

        log.info("현재 로그인된 사용자의 id(pk): {}", user.getId());
        log.info("현재 로그인된 사용자의 아이디(userId): {}", user.getEmail());
        log.info("현재 로그인된 사용자의 비밀번호(password): {}", user.getPassword());
        log.info("현재 로그인된 사용자의 유저네임(nickname): {}", user.getName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(user.toString());
    }
}
