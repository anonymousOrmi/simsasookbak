package com.simsasookbak.member.service;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.Status;
import com.simsasookbak.member.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {
    private final MemberRepository repository;

    public UserDetailService(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return repository.findByEmailAndStatusEquals(email, Status.GENERAL.getState())
                .orElseThrow(() -> new IllegalArgumentException(email));
    }

    public Member loadUserById(Long id) {
        return repository.findUserById(id);
    }
}
