package com.simsasookbak.member.domain;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    private String email;

    private String name;

    private String password;

    private String role;

    private LocalDate birthDate;

    private String phone;

    public Member toEntity() {
        return new Member(this.email, this.name, this.password, this.role, this.birthDate, "일반", this.phone);
    }
}
