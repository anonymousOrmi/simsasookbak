package com.simsasookbak.review.domain;

import com.simsasookbak.accommodation.domain.Accommodation;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicInsert;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

    @Column(name = "content", length = 300, nullable = false)
    private String content;

    @Column(name = "score", nullable = false)
    @ColumnDefault("0")
    private Integer score;

    @Column(name = "is_deleted", nullable = false, columnDefinition = "tinyint(1)")
    @ColumnDefault("0")
    @Comment("삭제여부")
    private Boolean isDeleted;

//    @Column(name = "room_title")
//    @ColumnDefault("'일반실'")
//    private String roomTitle;

    public void changeToDelete(){
        this.isDeleted=true;
    }
    public void modify(
            String content,
            Integer score
    ) {
        this.content = content;
        this.score = score;
    }
}
