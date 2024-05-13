package com.simsasookbak.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MemberDto {

    Long id;
    String email;
    String name;
    String role;
    LocalDate birthDate;
    String phone;


    public MemberDto(Long id, String email, String name, Role role, LocalDate birthDate, String phone) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role.toString();
        this.birthDate = birthDate;
        this.phone = phone;
    }
}
