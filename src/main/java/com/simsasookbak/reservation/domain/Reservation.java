package com.simsasookbak.reservation.domain;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.global.BaseEntity;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.room.domain.Room;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    @Column(name = "status", length = 10, nullable = false)
    @ColumnDefault("'대기'")
    @Comment("예약 상태(대기, 완료, 만료, 취소)")
    private String status;

    @Column(name = "start_date", nullable = false)
    @Comment("예약 시작일")
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    @Comment("예약 종료일")
    private LocalDate endDate;

    @Column(name = "request", length = 1000)
    @Comment("특이사항")
    private String request;

    @Builder
    public Reservation(Member member, Accommodation accommodation, Room room, String status, LocalDate startDate,
                       LocalDate endDate, String request) {
        this.member = member;
        this.accommodation = accommodation;
        this.room = room;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.request = request;
    }
}
