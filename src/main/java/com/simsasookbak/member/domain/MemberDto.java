package com.simsasookbak.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MemberDto {
    String email;
    String name;
    String role;
    LocalDate birthDate;
    String phone;

}
