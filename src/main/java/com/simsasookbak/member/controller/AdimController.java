package com.simsasookbak.member.controller;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.MemberDto;
import com.simsasookbak.member.dto.MemberResponseDto;
import com.simsasookbak.member.service.AdminService;
import com.simsasookbak.member.service.MemberService;
import com.simsasookbak.reservation.dto.response.ReservationResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class AdimController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/adminPage")
    public String goAdminPage(JoinPoint joinPoint, Model model){
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Member member = (Member) authentication.getPrincipal();
//        log.error("{}", member.getName());
//        member = memberService.findById(member.getId());
//        MemberResponseDto memberDto = member.toDto();
//
////        MemberResponseDto memberDto = (MemberResponseDto)joinPoint.getSignature();
//
//        model.addAttribute("member", memberDto);
        return "adminPage";
    }


    //모든 유저 정보 가져오기

    @GetMapping("/getAllMember")
    public String getAllMember(Model model){
        List<Member> members = adminService.findAllMember();

        List<MemberResponseDto> memberDtos = members.stream()
                .map(MemberResponseDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("members", memberDtos);
        return "adminPage";
    }



}
