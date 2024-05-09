package com.simsasookbak.member.controller;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.MemberDto;
import com.simsasookbak.member.service.MemberService;
import com.simsasookbak.member.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class MyPageController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/mypage")
    public String goMyPage(JoinPoint joinPoint,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        log.error("{}", member.getName());
        member = memberService.findById(member.getId());
        MemberDto memberDto = member.toDto();

//        MemberDto memberDto = (MemberDto)joinPoint.getSignature();

        model.addAttribute("member", memberDto);
        return "mypageInfo";
    }

    @PutMapping("/memberinfo/{email}/change")
    public String changeMemberInfo(@PathVariable String email, @ModelAttribute MemberDto memberDto){
        log.error("memberDto = {}",memberDto);
        memberService.editMemberInfo(email,memberDto);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Authentication newAuth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials());
//        SecurityContextHolder.getContext().setAuthentication(newAuth);
        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication,memberDto.getEmail()));
        return "redirect:/";
    }

    //탈퇴
    @DeleteMapping("/memberinfo/{email}/delete")
    public void deleteMember(@PathVariable String email){
        memberService.deleteMember(email);
    }

    protected Authentication createNewAuthentication(Authentication currentAuth, String username) {
        UserDetails newPrincipal = userDetailService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
        newAuth.setDetails(currentAuth.getDetails());
        return newAuth;
    }
}
