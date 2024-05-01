package com.simsasookbak.accommodation.domain;

import com.simsasookbak.global.BaseEntity;
import com.simsasookbak.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;

@Getter
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SQLDelete(sql = "UPDATE board SET is_deleted = true WHERE id = ?")
public class Accommodation extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodation_id", updatable = false)
    private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "content", length = 1000, nullable = false)
	private String content;

	@Column(name = "region", length = 10, nullable = false)
	private String region;

	@Column(name = "check_in", nullable = false)
	@Temporal(TemporalType.TIME)
	private String checkIn;

	@Column(name = "check_out", nullable = false)
	@Temporal(TemporalType.TIME)
	private String checkOut;

	@Column(name = "is_deleted", nullable = false, columnDefinition = "tinyint(1)")
	@ColumnDefault("0")
	@Comment("삭제여부")
	private Boolean isDeleted;

	@Builder
	public Accommodation(Long id, String name, String content, String region, String checkIn, String checkOut,
						 Boolean isDeleted) {
		this.id = id;
		this.name = name;
		this.content = content;
		this.region = region;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.isDeleted = isDeleted;
	}

/*ai_external_id`	bigint(20)	NOT NULL,
	member_id`	bigint(20)	NOT NULL*/


}
