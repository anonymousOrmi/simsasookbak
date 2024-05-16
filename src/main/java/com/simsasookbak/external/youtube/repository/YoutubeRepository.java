package com.simsasookbak.external.youtube.repository;

import com.simsasookbak.external.youtube.domain.Youtube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeRepository extends JpaRepository<Youtube, Long> {
}
