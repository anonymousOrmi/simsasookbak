package com.simsasookbak.accommodation.controller;

import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.dto.request.AccommodationRequest;
import com.simsasookbak.accommodation.dto.response.AccommodationResponse;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.review.dto.ReviewDto;
import com.simsasookbak.review.service.ReviewService;
import java.util.List;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final RoomService roomService;
    private final ReviewService reviewService;


    @GetMapping
    public String showAccommodations(
            @ModelAttribute AccommodationRequest request,
            Model model
    ) {
        List<AccommodationResponse> response = accommodationService.searchAccommodations(request);
        model.addAttribute("accommodations", response);
        return "/accommodations";
    }

    //상세 페이지 조회 (영석)
    @GetMapping("/{acom_id}")
    public String details(@PathVariable Long acom_id, Model model) {

        AccommodationDto accommodation = accommodationService.findAccommodationById(acom_id);
        List<RoomDto> roomList = roomService.findRoomByAcomId(acom_id);
        String exSummary = reviewService.findExSummaryByAcomId(acom_id);
        String inSummary = reviewService.findInSummaryByAcomId(acom_id);
        List<ReviewDto> reviewList = reviewService.findAllReviewByAcomId(acom_id);
        List<String> imgList = accommodationService.findImgByAcomId(acom_id);

        model.addAttribute("accommodation", accommodation);
        model.addAttribute("roomList", roomList);
        model.addAttribute("exSummary", exSummary);
        model.addAttribute("inSummary", inSummary);
        model.addAttribute("reviewList",reviewList);
        model.addAttribute("imgList",imgList);

        return "details";
    }

    //예약 성공 메세지 전송 (상형)
    @PostMapping("/reservation/message")
    public void reservationMessage(){

    }

    //리뷰 등록 페이지 이동
    @GetMapping("/{acom_id}/comment")
    public String review(@PathVariable Integer acom_id) {
        return "review-register";
    }
}

