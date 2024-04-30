package com.simsasookbak.review.domain;

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
public class ReviewImage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_image_id", updatable = false)
    private Long id;

    /*`review_id`	bigint(20)	NOT NULL,*/

    @Column(name = "url", nullable = false, columnDefinition = "text")
	private String url;
}
