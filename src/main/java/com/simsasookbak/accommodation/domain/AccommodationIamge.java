package com.simsasookbak.accommodation.domain;

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
public class AccommodationIamge extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodation_image", updatable = false)
    private Long id;
    /*        `accomodation_id`	bigint(20)	NOT NULL	COMMENT '숙박 ID',*/

    @Column(name = "url", nullable = false, columnDefinition = "text")
    private String url;
}
