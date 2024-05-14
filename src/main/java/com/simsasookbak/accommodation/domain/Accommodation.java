package com.simsasookbak.accommodation.domain;

import com.simsasookbak.accommodation.dto.AccommodationUpdateDto;
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
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
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
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;

	@Column(name = "name", length = 100, nullable = false)
	private String name;

	@Column(name = "content", length = 1000, nullable = false)
	private String content;

	@Column(name = "region", length = 10, nullable = false)
	private String region;

	@Column(name = "address", length = 20, nullable = false)
	private String address;

	@Column(name = "check_in", nullable = false)
	private LocalTime checkIn;

	@Column(name = "check_out", nullable = false)
	private LocalTime checkOut;

	@Column(name = "is_deleted", nullable = false, columnDefinition = "tinyint(1)")
	@ColumnDefault("0")
	@Comment("삭제여부")
	private Boolean isDeleted;

	@OneToMany(mappedBy = "accommodation")
	private List<AccommodationFacilityMapping> accommodationFacilityMappingList = new ArrayList<>();

	@Builder
	public Accommodation(Long id, Member member, String name, String content, String region, String checkIn, String checkOut,
						 Boolean isDeleted, String address) {
		this.id = id;
		this.member = member;
		this.name = name;
		this.content = content;
		this.region = region;
		this.checkIn = LocalTime.parse(checkIn);
		this.checkOut = LocalTime.parse(checkOut);
		this.isDeleted = isDeleted;
		this.address = address;
	}

	public void update(AccommodationUpdateDto requestDto) {
		this.name = requestDto.getName();
		this.content = requestDto.getContent();
		this.region = requestDto.getContent();
		this.checkIn = LocalTime.parse(requestDto.getCheckIn());
		this.checkOut = LocalTime.parse(requestDto.getCheckOut());
		this.address = requestDto.getAddress();
	}

	public void changeToDelete(){
		this.isDeleted=true;
	}

}
