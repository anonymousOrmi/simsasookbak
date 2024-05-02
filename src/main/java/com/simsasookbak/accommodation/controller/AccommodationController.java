package com.simsasookbak.accommodation.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.review.service.ReviewService;
import com.simsasookbak.room.domain.Room;
import java.util.List;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequiredArgsConstructor
@RequestMapping("accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final RoomService roomService;
    private final ReviewService reviewService;

    //상세 페이지 조회 (영석)
    @GetMapping("/{acom_id}")
    public String details(@PathVariable Long acom_id){
        Accommodation accommodation = accommodationService.findAccommodationById(acom_id);
        List<Room> roomList = roomService.findRoomByAcomId(acom_id);
        String exSummary = reviewService.findExSummaryByAcomId(acom_id);
        String inSummary = reviewService.findInSummaryByAcomId(acom_id);
//        List<String> imgList = accommodationService.findImgByAcomId(acom_id);

        return "details";
    }

    @GetMapping("/{acom_id}/{room_id}/reservationPage")
    public String viewReservationPage(@PathVariable Long acom_id, @PathVariable Long room_id, Model model) {

//        Accommodation accommodation = accommodationService.findAccommodationById(acom_id);
        RoomDto room = roomService.findRoomById(room_id);
//        model.addAttribute("accommodation", accommodation);
        model.addAttribute("room", room);

        return "reservationPage";
    }

    //예약 성공 메세지 전송 (상형)
    @PostMapping("/reservation/message")
    public void reservationMessage(){

    }

    @PostMapping("/{acom_id}/comment")
    public void review(@PathVariable Integer acom_id) {

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

}
