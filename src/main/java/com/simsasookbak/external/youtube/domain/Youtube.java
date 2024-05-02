package com.simsasookbak.external.youtube.domain;

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
@NoArgsConstructor
@AllArgsConstructor
@Comment("Youtube")
public class Youtube extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "youtube_id", updatable = false)
    private Long id;

    @Column(name = "video_id", nullable = false, length = 100, unique = true)
    @Comment("Video ID")
    private String videoId;
    public void update(
            String videoId
    ) {
        this.videoId = videoId;
    }
}
