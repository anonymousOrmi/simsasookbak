package com.simsasookbak.reservation.domain;

import com.simsasookbak.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;
import lombok.AllArgsConstructor;
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

    @Column(name = "status", length = 10, nullable = false)
    @ColumnDefault("'대기'")
    @Comment("예약 상태(대기, 완료, 만료, 취소)")
	private String status;

    @Column(name = "start_date", nullable = false)
    @Comment("예약 시작일")
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @Comment("예약 종료일")
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(name = "request", length = 1000)
    @Comment("특이사항")
    private String request;
    /*private  user_id 	bigint(20)	NOT NULL,*/
	/*private  room_id 	bigint(20)	NOT NULL	DEFAULT NOT NULL*/
}
