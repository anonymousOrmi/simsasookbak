package com.simsasookbak.review.domain;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.global.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @Column(name = "summary", length = 1000, nullable = false)
    @Comment("자체 사이트 리뷰 요약")
    private String summary;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

}
