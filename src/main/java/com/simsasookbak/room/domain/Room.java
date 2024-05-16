package com.simsasookbak.room.domain;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.global.BaseEntity;
import com.simsasookbak.room.dto.RoomUpdateDto;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room extends BaseEntity {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id", updatable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accommodation_id")
	private Accommodation accommodation;

	@Column(name = "name", length = 15, nullable = false)
	private String name;

	@Column(name ="cost", nullable = false)
	private Integer cost;

	@Column(name = "content", length = 1000, nullable = false)
	private String content;

	@Column(name = "use_guide", length = 1000, nullable = false)
	private String useGuide;


	@OneToMany(mappedBy = "room")
	private List<RoomFacilityMapping> roomFacilityMappingList = new ArrayList<>();

	@Setter
	@Column(name = "is_deleted", nullable = false, columnDefinition = "tinyint(1)")
	@ColumnDefault("0")
	@Comment("삭제여부")
	private Boolean isDeleted;

	public void update(RoomUpdateDto updateDto) {
		this.name = updateDto.getName();
		this.cost = updateDto.getCost();
		this.content = updateDto.getContent();
		this.useGuide = updateDto.getUseGuide();
	}

	public void changeToDelete(){
		this.isDeleted=true;
	}
}
