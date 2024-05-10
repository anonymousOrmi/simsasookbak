package com.simsasookbak.reservation.controller;

import com.simsasookbak.member.domain.Member;
import com.simsasookbak.reservation.dto.ReservationAddRequestDto;
import com.simsasookbak.reservation.dto.ReservationAddResponseDto;
import com.simsasookbak.reservation.dto.response.ReservationResponseDto;
import com.simsasookbak.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.simsasookbak.reservation.dto.request.PopularRegionRequest;
import com.simsasookbak.reservation.dto.response.ReservationResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    //TODO: 회원정보 받아오기
    @PostMapping("/{accommodationId}/{roomId}")
    public ResponseEntity<ReservationAddResponseDto> saveReservation(@PathVariable Long accommodationId,
                                                                     @PathVariable Long roomId,
                                                                     @RequestBody ReservationAddRequestDto request) {

        ReservationAddResponseDto responseDto = reservationService.save(accommodationId, roomId, request);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/popular-region")
    public ResponseEntity<List<ReservationResponse>> getPopularRegionsByDate(
            @ModelAttribute PopularRegionRequest request) {
        List<ReservationResponse> response = reservationService.findPopularRegionsByDate(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public String reservationList(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Member member = (Member) authentication.getPrincipal();

        List<ReservationResponseDto> reservationList = reservationService.findAllReservationByMemberId(member.getId());

        model.addAttribute("reservationList", reservationList);

        return "my-reservation-list";
    }
}