package com.simsasookbak.member.dto;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.member.domain.MemberDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.checkerframework.checker.units.qual.A;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {

    private Long id;

    private String email;

    private String name;

    private String role;

    private LocalDate birthDate;

    private String status;

    private String phone;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public static MemberResponseDto toDto(Member member) {
        return new MemberResponseDto(
                member.getId(),
                member.getEmail(),
                member.getName(),
                member.getRole(),
                member.getBirthDate(),
                member.getStatus(),
                member.getPhone(),
                member.getCreatedAt(),
                member.getUpdatedAt()
        );
    }

}
