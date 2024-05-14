package com.simsasookbak.member.service;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.repository.MemberRepository;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserDetailService implements UserDetailsService {

    private final MemberRepository repository;

    public UserDetailService(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
//        return repository.findByEmail(email).orElseThrow(()->new IllegalArgumentException(email));
        if (email == null || email.isEmpty()) {
            log.error("user Email cannot be null or empty");
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return repository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));
    }

    public Member loadUserById(Long id) {
        return repository.findUserById(id);
    }



}
