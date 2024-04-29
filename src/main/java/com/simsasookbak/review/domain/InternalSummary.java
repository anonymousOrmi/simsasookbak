package com.simsasookbak.review.domain;

import com.simsasookbak.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Builder
@Entity
@Comment("내부 숙박 리뷰 요약")
@NoArgsConstructor
@AllArgsConstructor
public class InternalSummary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "internal_summary_id")
    private Long id;
	@Column(name = "summary", length = 300, nullable = false)
    @Comment("자체 사이트 리뷰 요약")
    private String summary;
    // latest_review_id // 최신 리뷰 ID
}
