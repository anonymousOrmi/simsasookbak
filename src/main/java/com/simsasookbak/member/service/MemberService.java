package com.simsasookbak.member.service;


import com.simsasookbak.member.domain.AddUserDto;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService  {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    public void register(Member member){
        Member member1 = new Member(member.getEmail(),member.getName(), encoder.encode(member.getPassword()), member.getRole(),member.getBirthDate(),member.getStatus(),member.getPhone());
        memberRepository.save(member1);
    }


}
