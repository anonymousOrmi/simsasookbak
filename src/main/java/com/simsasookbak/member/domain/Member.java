package com.simsasookbak.member.domain;

import com.simsasookbak.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
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

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "birth_date", nullable = false)
    @Comment("생년월일")
    private LocalDate birthDate;

    @Column(name = "status", length = 10,  nullable = false)
    @ColumnDefault(value = "'일반'")
    @Comment("상태 (일반/탈퇴)")
    private String status;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @ElementCollection
    private Collection<GrantedAuthority> authorities;

    public Member(String email,String name,String password,String role,LocalDate birthDate, String status,String phone){
        this.email=email;
        this.name=name;
        this.password=password;
        this.role= Role.valueOf(role);
        this.birthDate=birthDate;
        this.status=status;
        this.phone=phone;

    }

    private void createAuthorities(Role role){
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.getName()));

        this.authorities=authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.toString()));
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
        return new MemberDto(this.id,this.email,this.name,this.role,this.birthDate,this.phone);
    }

    //회원정보 수정
    public void editInfo(String name,String phone){
        this.name=name;
        this.phone=phone;
    }

    //탈퇴처리
    public void cancellation(){
        this.status="탈퇴";
        createAuthorities(Role.LEAVER);
        this.role=Role.LEAVER;
    }

    public void updateRole(Role newRole){
        this.role = newRole;
    }
}
