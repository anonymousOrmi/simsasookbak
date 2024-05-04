package com.simsasookbak.accommodation.controller;

import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.service.AccommodationService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.simsasookbak.review.dto.ScoreAverageDto;
import com.simsasookbak.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("accommodation")
public class AccommodationRestController {

    private final AccommodationService accommodationService;
    private final ReviewService reviewService;

    @GetMapping("/search")
    public ResponseEntity<?> searchAccommodation(@RequestParam String keyword,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                 @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<AccommodationDto> accommodations;
        String message;
        HttpStatus status;

        if (keyword != null) {
            accommodations = accommodationService.searchAccommodation(keyword);
            message = accommodations.isEmpty() ? "검색 내용이 없습니다." : null;
            status = accommodations.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        } else if (startDate != null && endDate != null) {
            accommodations = accommodationService.searchDate(startDate, endDate);
            message = accommodations.isEmpty() ? "예약 가능한 숙박 시설이 없습니다." : null;
            status = accommodations.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        } else {
            return ResponseEntity.badRequest().body("잘못된 요청입니다.");
        }

        if (message != null) {
            return ResponseEntity.status(status).body(message);
        }

        // 공통된 값만 반환
        Set<AccommodationDto> uniqueAccommodations = new HashSet<>(accommodations);
        return ResponseEntity.ok().body(new ArrayList<>(uniqueAccommodations));
    }

    @GetMapping("/getHighScoreAccommodation")
    public ResponseEntity<?> getHighScoreAccommodation() {
        try {
            // 상위 평점 6개의 숙소 정보를 가져옵니다.
            List<AccommodationDto> highScoreAccommodations = accommodationService.getHighScoreAccommodation();

            // 숙소 정보를 정상적으로 가져온 경우 200 OK 응답과 함께 숙소 정보를 반환합니다.

            // 숙소 정보를 정상적으로 가져온 경우, for 문을 사용하여 각 숙소 정보를 확인합니다.
            for (AccommodationDto accommodationDto : highScoreAccommodations) {
                System.out.println("Accommodation ID: " + accommodationDto.getId());
            }

            return ResponseEntity.ok(highScoreAccommodations);
        } catch (Exception e) {
            // 에러가 발생한 경우 500 Internal Server Error 응답을 반환합니다.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve high score accommodations.");
        }
    }

//    @GetMapping("/findScoreSixAccommodation")
//    public ResponseEntity<?> findScoreSixAccommodation() {
//        try {
//            // 상위 평점 6개의 숙소 정보를 가져옵니다.
//            List<Long> highScoreAccommodations = reviewService.findScoreSixAccommodation();
//
//            // 숙소 정보를 정상적으로 가져온 경우 200 OK 응답과 함께 숙소 정보를 반환합니다.
//            return ResponseEntity.ok(highScoreAccommodations);
//        } catch (Exception e) {
//            // 에러가 발생한 경우 500 Internal Server Error 응답을 반환합니다.
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to retrieve high score accommodations.");
//        }
//    }
}
