package com.simsasookbak.member.controller;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.MemberDto;
import com.simsasookbak.member.domain.MemberRequestDto;
import com.simsasookbak.member.domain.Role;
import com.simsasookbak.member.service.MemberService;
import com.simsasookbak.member.service.UserDetailService;
import java.util.List;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class MyPageController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserDetailService userDetailService;

    @GetMapping("/mypage")
    public String goMyPage(JoinPoint joinPoint,Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        member = memberService.findById(member.getId());
        MemberDto memberDto = member.toDto();
        model.addAttribute("member", memberDto);
        return "mypageInfo";
    }

    @PutMapping("/memberinfo/{id}/change")
    public ResponseEntity<Void> changeMemberInfo(@PathVariable Long id, @RequestBody MemberRequestDto data){

        String name = data.getName();
        String phone = data.getPhone();
        memberService.updateMemberInfo(id,name,phone);


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(authentication,id));

        return ResponseEntity.ok().build();
    }

    //탈퇴
    @DeleteMapping("/memberinfo/{email}/delete")
    public void deleteMember(@PathVariable String email){
        memberService.deleteMember(email);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(changeAuth(authentication,email));
    }

    protected Authentication createNewAuthentication(Authentication currentAuth, Long id) {
        UserDetails newPrincipal = userDetailService.loadUserById(id);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), newPrincipal.getAuthorities());
        newAuth.setDetails(currentAuth.getDetails());
        return newAuth;
    }
    protected Authentication changeAuth(Authentication currentAuth, String username) {
        UserDetails newPrincipal = userDetailService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken newAuth = new UsernamePasswordAuthenticationToken(newPrincipal, currentAuth.getCredentials(), List.of(new SimpleGrantedAuthority(Role.LEAVER.getName())));
        newAuth.setDetails(currentAuth.getDetails());
        return newAuth;
    }
}
