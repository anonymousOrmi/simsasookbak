package com.simsasookbak.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
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
        this.role = role.getName();
        this.birthDate = birthDate;
        this.phone = phone;
    }
}
