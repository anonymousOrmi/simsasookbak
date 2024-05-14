package com.simsasookbak.member.controller;

import com.simsasookbak.accommodation.dto.request.AccommodationRequest;
import com.simsasookbak.accommodation.dto.response.AccommodationResponse;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.dto.MemberResponseDto;
import com.simsasookbak.member.service.AdminService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //모든 유저 정보 가져오기

//    @GetMapping("/getAllMember")
//    public String getAllMember(Model model){
//        List<Member> members = adminService.findAllMember();
//
//        List<MemberResponseDto> memberDtos = members.stream()
//                .map(MemberResponseDto::toDto)
//                .collect(Collectors.toList());
//
//        model.addAttribute("members", memberDtos);
//        return "adminPage";
//    }

    @GetMapping("/getAllMember")
    public String getAllMember(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            Model model
    ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Member> membersPage = adminService.findAllMembersPaged(pageable);

        model.addAttribute("members", membersPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", membersPage.getTotalPages());
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

//    @GetMapping
//    public String showMembers(
//            @ModelAttribute AccommodationRequest request,
//            @RequestParam(value = "page", defaultValue = "0") int pageNum,
//            Model model
//    ) {
//        Page<AccommodationResponse> response = accommodationService.searchAccommodations(request, pageNum);
//        model.addAttribute("currentPage", response.getNumber());
//        model.addAttribute("totalPages", response.getTotalPages());
//
//        model.addAttribute("request", request);
//        model.addAttribute("accommodations", response);
//
//        return "list-page";
//    }


    // 서버 컨트롤러 메서드 수정
    @PostMapping("/delete")
    public String deleteUser(@RequestParam("memberId") long memberId) {
        adminService.deleteMember(memberId);
        return "redirect:/admin"; // 관리자 페이지로 리다이렉트
    }




}
