package com.simsasookbak.review.domain;

import com.simsasookbak.global.BaseEntity;
import jakarta.persistence.*;
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
    @Column(name = "review_image_id", updatable = false) //, columnDefinition = "bigint AUTO_INCREMENT"
    private Long id;

    /*`review_id`	bigint(20)	NOT NULL,*/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    @Column(name = "url", nullable = false, columnDefinition = "text")
	private String url;

    public ReviewImage(Review review,String url){
        this.review=review;
        this.url=url;
    }
}
