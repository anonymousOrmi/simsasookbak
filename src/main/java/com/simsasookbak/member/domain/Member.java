package com.simsasookbak.member.domain;

import com.simsasookbak.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long member_id;

    @Column(name = "email", nullable = false, length = 5, unique = true)
    private String email;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "role", nullable = false, length = 10)
    @Comment("권한 (이용자/사업자/관리자)")
    private String role;

    @Column(name = "birth_date", nullable = false, columnDefinition= "date comment '생년월일'")
    private String birthDate;

    @Column(name = "status", length = 10, columnDefinition = "not null default '일반',")
    @Comment("상태 (일반/탈퇴)")
    private String status;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;
}
