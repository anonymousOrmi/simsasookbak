package com.simsasookbak.global.page.controller;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.reservation.dto.ReservationUnableDto;
import com.simsasookbak.reservation.service.ReservationService;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final AccommodationService accommodationService;
    private final RoomService roomService;
    private final ReservationService reservationService;


    @GetMapping("/{accommodationId}/{roomId}/reservationPage")
    public String viewReservationPage(@PathVariable Long accommodationId, @PathVariable Long roomId, Model model) {

        Accommodation findAccommodation = accommodationService.findAccommodationById(accommodationId);
        AccommodationDto accommodation = AccommodationDto.toAccommodationDto(findAccommodation);
        RoomDto room = roomService.findRoomById(roomId);
        ReservationUnableDto reservationUnable = reservationService.getReservationUnableDates(roomId);

        model.addAttribute("accommodation", accommodation);
        model.addAttribute("room", room);
        model.addAttribute("reservationUnable", reservationUnable);

        return "reservationPage";
    }

}
