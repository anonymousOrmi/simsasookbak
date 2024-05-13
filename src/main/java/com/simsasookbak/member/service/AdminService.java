package com.simsasookbak.member.service;

import static com.simsasookbak.global.util.ConvertToDateTime.convertToLocalDate;

import com.simsasookbak.accommodation.dto.request.AccommodationRequest;
import com.simsasookbak.accommodation.dto.response.AccommodationResponse;
import com.simsasookbak.accommodation.dto.response.AccommodationView;
import com.simsasookbak.member.domain.Member;

import com.simsasookbak.member.dto.MemberResponseDto;
import com.simsasookbak.member.repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final MemberRepository memberRepository;


//    public List<Member> findAllMembersPaged() {
//        List<Member> members = memberRepository.getAllMember()
//                .orElseThrow(() -> new NoSuchElementException("Member list is empty"));
//
//        return members;
//    }

    public Page<Member> findAllMembersPaged(int pageNum, int pageSize) {
        PageRequest pageable = PageRequest.of(pageNum, pageSize);
        return memberRepository.findAll(pageable);
    }

    //유저 이름 검색
    public List<Member> searchMemberByName(String name) {
        return memberRepository.getSearchMemberByName(name)
                .orElseThrow(() -> new NoSuchElementException("No member found with name: " + name));
    }

    //유저 삭제 -> status 탈퇴로 변경
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found with id: " + memberId));
        member.setStatus("탈퇴");
        memberRepository.save(member);
    }






}
