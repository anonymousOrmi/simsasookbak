package com.simsasookbak.review.domain;

import com.simsasookbak.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false)
    private Long id;
/*	`member_id`	bigint(20)	NOT NULL,*/
	/*`accommodation_id`	bigint(20)	NOT NULL,*/

    @Column(name = "content", length = 3000, nullable = false)
    private String content;

    @Column(name = "score", nullable = false)
    @ColumnDefault("0")
    private Integer score;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "tinyint(1)")
    @ColumnDefault("0")
    @Comment("삭제여부")
    private Boolean isDeleted;

	/*`summary_id`	bigint	NOT NULL*/
}
