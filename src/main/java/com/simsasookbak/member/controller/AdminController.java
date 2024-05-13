package com.simsasookbak.member.controller;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.dto.MemberResponseDto;
import com.simsasookbak.member.service.AdminService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

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

    // 이름으로 유저 검색
    @GetMapping("/searchMember")
    public String searchMemberByName(@RequestParam("keyword") String name, Model model) {
        List<MemberResponseDto> memberDtos = adminService.searchMemberByName(name).stream()
                .map(MemberResponseDto::toDto)
                .collect(Collectors.toList());

        model.addAttribute("members", memberDtos);
        return "adminPage";
    }




}
