package com.simsasookbak.member.domain;

import com.simsasookbak.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.sql.Date;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    private String email;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "role", nullable = false, length = 10)
    @Comment("권한 (이용자/사업자/관리자)")
    private String role;

    @Column(name = "birth_date", nullable = false)
    @Comment("생년월일")
    @Temporal(TemporalType.DATE)
    private LocalDate birthDate;

    @Column(name = "status", length = 10,  nullable = false)
    @ColumnDefault(value = "'일반'")
    @Comment("상태 (일반/탈퇴)")
    private String status;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;
}
