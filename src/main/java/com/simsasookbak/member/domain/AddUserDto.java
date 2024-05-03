package com.simsasookbak.member.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddUserDto {
    private String email;
    private String password;
}
