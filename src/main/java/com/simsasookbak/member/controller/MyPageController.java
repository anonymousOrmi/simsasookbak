package com.simsasookbak.member.controller;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.MemberDto;
import com.simsasookbak.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class MyPageController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/mypage")
    public String goMyPage(JoinPoint joinPoint,Model model){
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MemberDto memberDto = ((Member) authentication.getPrincipal()).toDto();
//        MemberDto memberDto = (MemberDto)joinPoint.getSignature();
        log.error("{}", memberDto);
        model.addAttribute("member", memberDto);
        return "mypageInfo";
    }

    @PutMapping("/memberinfo/{email}/change")
    public String changeMemberInfo(@PathVariable String email, @ModelAttribute MemberDto memberDto){
        log.error("memberDto = {}",memberDto);
        memberService.editMemberInfo(email,memberDto);

        return "index";
    }

}
