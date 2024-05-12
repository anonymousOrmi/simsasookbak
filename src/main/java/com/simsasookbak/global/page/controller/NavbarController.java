package com.simsasookbak.global.page.controller;

import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.dto.response.AccommodationRegisteredResponse;
import com.simsasookbak.accommodation.dto.response.AccommodationResponse;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.member.domain.Member;
import com.simsasookbak.reservation.dto.response.ReservationResponseDto;
import com.simsasookbak.reservation.service.ReservationService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class NavbarController {

    private final AccommodationService accommodationService;
    private final ReservationService reservationService;

    @GetMapping("/management/myAccommodations")
    public String showMyAccommodations(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member)authentication.getPrincipal();

        List<AccommodationRegisteredResponse> myAccommodationList = accommodationService.findMyAccommodations(member.getId());
        model.addAttribute("myAccommodations", myAccommodationList);

        return "my-accommodation-list";
    }

    @GetMapping("/management/reservations")
    public String showMyReservations(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Member member = (Member)authentication.getPrincipal();

        List<AccommodationRegisteredResponse> myAccommodationList = accommodationService.findMyAccommodations(member.getId());
        List<ReservationResponseDto> myReservationList = new ArrayList<>();
        for (AccommodationRegisteredResponse accommodation : myAccommodationList) {
            myReservationList.addAll(reservationService.findReservationByAccommodationId(accommodation.getAccommodationId()));
        }

        model.addAttribute("myReservations", myReservationList);

        return "reservation-management";
    }
}
