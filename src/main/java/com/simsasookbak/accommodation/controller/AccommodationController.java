package com.simsasookbak.accommodation.controller;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.request.AccommodationRequest;
import com.simsasookbak.accommodation.dto.response.AccommodationResponse;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.review.service.ReviewService;
import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.service.RoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

    // TODO 숙소 목록
    @GetMapping
    public String showAccommodations(@ModelAttribute AccommodationRequest request) {
        List<AccommodationResponse> response = accommodationService.searchAccommodations(request);

        return "/accommodations";
    }

    //상세 페이지 조회 (영석)
    @GetMapping("/{acom_id}")
    public String details(@PathVariable Long acom_id) {
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
    public void reservationMessage() {

    }

    @PostMapping("/{acom_id}/comment")
    public void review(@PathVariable Integer acom_id) {
    }


}