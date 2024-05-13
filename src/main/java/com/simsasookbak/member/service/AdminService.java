package com.simsasookbak.member.service;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.MemberDto;
import com.simsasookbak.member.dto.MemberResponseDto;
import com.simsasookbak.member.repository.MemberRepository;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final MemberRepository memberRepository;


    public List<Member> findAllMember() {
        List<Member> members = memberRepository.getAllMember()
                .orElseThrow(() -> new NoSuchElementException("Member list is empty"));

        return members;
    }

    //유저 이름 검색
    public List<Member> searchMemberByName(String name) {
        return memberRepository.getSearchMemberByName(name)
                .orElseThrow(() -> new NoSuchElementException("No member found with name: " + name));
    }





}
