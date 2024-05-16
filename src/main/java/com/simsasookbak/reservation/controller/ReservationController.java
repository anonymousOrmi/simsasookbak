package com.simsasookbak.reservation.controller;

import com.simsasookbak.global.aop.MethodInvocationLimit;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.reservation.dto.ReservationAddRequestDto;
import com.simsasookbak.reservation.dto.ReservationAddResponseDto;
import com.simsasookbak.reservation.dto.ReservationUnableDto;
import com.simsasookbak.reservation.dto.response.ReservationResponseDto;
import com.simsasookbak.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @MethodInvocationLimit
    @PostMapping("/{accommodationId}/{roomId}")
    public ResponseEntity<ReservationAddResponseDto> saveReservation(@AuthenticationPrincipal Member member, @PathVariable Long accommodationId,
                                                                     @PathVariable Long roomId,
                                                                     @RequestBody ReservationAddRequestDto request) {

        ReservationAddResponseDto responseDto = reservationService.save(member, accommodationId, roomId, request);

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

    @GetMapping("/renew/{reservation_id}")
    public String updateReservation(@PathVariable Long reservation_id, Model model) {

        ReservationResponseDto reservationDto = reservationService.findReservationById(reservation_id);
        ReservationUnableDto reservationUnable = reservationService.getReservationUnableDates(reservationDto.getRoom().getId());

        model.addAttribute("reservation",reservationDto);
        model.addAttribute("reservationUnable", reservationUnable);


        return "updateReservationPage";
    }
}