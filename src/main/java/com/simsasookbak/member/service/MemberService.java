package com.simsasookbak.member.service;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public void register(Member member){
        memberRepository.save(member);
    }
}
