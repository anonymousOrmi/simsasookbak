package com.simsasookbak.member.domain;

import com.simsasookbak.global.BaseEntity;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity
@Table(name="member")
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity implements UserDetails {
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
    private LocalDate birthDate;

    @Column(name = "status", length = 10,  nullable = false)
    @ColumnDefault(value = "'일반'")
    @Comment("상태 (일반/탈퇴)")
    private String status;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;


    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Member(String email,String name,String password,String role,LocalDate birthDate, String status,String phone){
        this.email=email;
        this.name=name;
        this.password=password;
        this.role=role;
        this.birthDate=birthDate;
        this.status=status;
        this.phone=phone;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public MemberDto toDto(){
        return new MemberDto(this.email,this.name,this.role,this.birthDate,this.phone);
    }

    public void editInfo(String name,String phone){
        this.name=name;
        this.phone=phone;
    }
}
