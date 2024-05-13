package com.simsasookbak.accommodation.controller;

import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.dto.request.AccommodationRequest;
import com.simsasookbak.accommodation.dto.request.AccommodationAndRoomsAddRequestDto;
import com.simsasookbak.accommodation.dto.AccommodationUpdateDto;
import com.simsasookbak.accommodation.dto.response.AccommodationAddResponseDto;
import com.simsasookbak.accommodation.dto.response.AccommodationResponse;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.global.aop.MethodInvocationLimit;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.reservation.service.ReservationService;
import com.simsasookbak.review.dto.ReviewDto;
import com.simsasookbak.review.service.ReviewService;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.service.RoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("accommodation") // 앞에  / 없어도 되나
@Slf4j
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final RoomService roomService;
    private final ReviewService reviewService;
    private final ReservationService reservationService;


    @GetMapping
    public String showAccommodations(
            @ModelAttribute AccommodationRequest request,
            @RequestParam(value = "page", defaultValue = "0") int pageNum,
            Model model
    ) {
        Page<AccommodationResponse> response = accommodationService.searchAccommodations(request, pageNum);
        model.addAttribute("currentPage", response.getNumber());
        model.addAttribute("totalPages", response.getTotalPages());

        model.addAttribute("request", request);
        model.addAttribute("accommodations", response);

        return "list-page";
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
        model.addAttribute("reviewList", reviewList);
        model.addAttribute("imgList", imgList);

        return "details";
    }


    //리뷰 등록 페이지 이동
    @GetMapping("/{acom_id}/comment")
    public String review(@PathVariable Long acom_id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member) authentication.getPrincipal();
        model.addAttribute("accommodation", acom_id);
        model.addAttribute("member", member.getId());
        model.addAttribute("RoomNames", reservationService.getReservationRoomName(acom_id, member.getId()));
        return "review-register";
    }

    @MethodInvocationLimit
    @PostMapping("/registerPage/register")
    public ResponseEntity<AccommodationAddResponseDto> registerAccommodationAndRooms(@AuthenticationPrincipal Member member,
                                                                                     @RequestBody AccommodationAndRoomsAddRequestDto accommodationAndRoomsAddRequestDto) {
        AccommodationAddResponseDto response = accommodationService.save(member, accommodationAndRoomsAddRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @MethodInvocationLimit
    @PutMapping("/{accommodationId}/accommodationUpdate")
    public ResponseEntity<AccommodationUpdateDto> updateAccommodation(@PathVariable Long accommodationId, @RequestBody
    AccommodationUpdateDto accommodationUpdateDto) {

        accommodationService.updateAccommodation(accommodationId, accommodationUpdateDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(accommodationUpdateDto);
    }

}
