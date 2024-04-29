package com.simsasookbak.room.domain;

import com.simsasookbak.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "room_id", updatable = false)
	private Long id;

	/*`accomodation_id`	bigint(20)	NOT NULL,*/

	@Column(name = "name", length = 15, nullable = false)
	private String name;

	@Column(name ="cost", nullable = false)
	private Integer cost;

	@Column(name = "content", length = 2000, nullable = false)
	private String content;

	@Column(name = "use_guide", length = 2000, nullable = false)
	private String useGuide;
}
