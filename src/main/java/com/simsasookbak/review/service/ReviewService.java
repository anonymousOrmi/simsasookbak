package com.simsasookbak.review.service;
import com.simsasookbak.review.dto.ScoreAverageDto;
import com.simsasookbak.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public String findExSummaryByAcomId(Long id) {
        return reviewRepository.findExSummaryByAcomId(id);
    }

    public String findInSummaryByAcomId(Long id) {
        return reviewRepository.findInSummaryByAcomId(id);
    }

    public List<Long> findScoreSixAccommodation() {
//         accommodation ID별 평균 점수를 조회합니다.
        List<ScoreAverageDto> averageScoresByAccommodationId = reviewRepository.findAverageScoreByAccommodationId();


        for (ScoreAverageDto scoreAverageDto : averageScoresByAccommodationId) {
            Long accommodationId = scoreAverageDto.getAccommodationId();
            Double averageScore = scoreAverageDto.getAverageScore();
            System.out.println("Accommodation ID: " + accommodationId + ", Average Score: " + averageScore);
        }


        // 상위 6개의 accommodation ID를 선택합니다.
        List<Long> topSixAccommodationIds = averageScoresByAccommodationId.stream()
                .limit(6)
                .map(ScoreAverageDto::getAccommodationId) // ScoreAverageDto에서 숙소 아이디만 추출합니다.
                .collect(Collectors.toList());


        return topSixAccommodationIds;



    }




}
