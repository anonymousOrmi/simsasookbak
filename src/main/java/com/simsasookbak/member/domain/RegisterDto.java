package com.simsasookbak.member.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;


import java.sql.Date;
import java.time.LocalDateTime;

@Getter
@Setter
public class RegisterDto {

    private String email;

    private String name;

    private String password;

    private String role;

    private Date birthDate;

    private String phone;

    public Member toEntity(){
        return new Member(this.email,this.name,this.password,this.role, new Date(101111010),"일반",this.phone);
//        return new Member("test@test.com","hi","1234","이용자", new Date(101111010),"일반","01011111111");
    }
}
