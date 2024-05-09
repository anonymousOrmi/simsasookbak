package com.simsasookbak.member.service;


import com.simsasookbak.external.ai.alan.event.RegistrationEvent;
import com.simsasookbak.member.domain.AddUserDto;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.MemberDto;
import com.simsasookbak.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService  {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;
    private final ApplicationEventPublisher publisher;

    public void register(Member member){
        Member member1 = new Member(member.getEmail(),member.getName(), encoder.encode(member.getPassword()), member.getRole(),member.getBirthDate(),member.getStatus(),member.getPhone());
        Member savedMember = memberRepository.save(member1);

        publisher.publishEvent(new RegistrationEvent(this, savedMember.getId()));
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow();
    }

    @Transactional
    public void editMemberInfo(String email,MemberDto memberDto){
        Member member = memberRepository.findByEmail(email).orElseThrow();
        member.editInfo(memberDto.getName(),memberDto.getPhone());
    }

    @Transactional
    public void deleteMember(String email){
        Member member = memberRepository.findByEmail(email).orElseThrow();
        member.cancellation();
    }

}
