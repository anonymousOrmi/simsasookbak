package com.simsasookbak.global.page.controller;

import com.simsasookbak.accommodation.domain.AccommodationFacility;
import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.service.AccommodationFacilityService;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.reservation.dto.ReservationUnableDto;
import com.simsasookbak.reservation.service.ReservationService;
import com.simsasookbak.room.domain.RoomFacility;
import com.simsasookbak.room.dto.RoomDto;
import com.simsasookbak.room.service.RoomFacilityService;
import com.simsasookbak.room.service.RoomService;
import java.util.List;
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
    private final AccommodationFacilityService accommodationFacilityService;
    private final RoomFacilityService roomFacilityService;

    @GetMapping("/accommodation/{accommodationId}/{roomId}/reservationPage")
    public String viewReservationPage(@PathVariable Long accommodationId, @PathVariable Long roomId, Model model) {
        AccommodationDto accommodation = accommodationService.findAccommodationById(accommodationId);
        RoomDto room = roomService.findRoomById(roomId);
        ReservationUnableDto reservationUnable = reservationService.getReservationUnableDates(roomId);

        model.addAttribute("accommodation", accommodation);
        model.addAttribute("room", room);
        model.addAttribute("reservationUnable", reservationUnable);

        return "reservationPage";
    }

    @GetMapping("/accommodation/registerPage")
    public String viewAccommodationRegisterPage(Model model) {
        List<AccommodationFacility> accommodationFacilityList = accommodationFacilityService.findAll();
        List<RoomFacility> roomFacilityList = roomFacilityService.findAll();

        model.addAttribute("accommodationFacilityList", accommodationFacilityList);
        model.addAttribute("roomFacilityList", roomFacilityList);

        return "accommodation-register";
    }

    @GetMapping("/accommodation/{accommodationId}/updatePage")
    public String viewAccommodationUpdatePage(@PathVariable Long accommodationId, Model model) {

        AccommodationDto accommodation = accommodationService.findAccommodationById(accommodationId);
        model.addAttribute("accommodation", accommodation);

        List<AccommodationFacility> accommodationFacilityList = accommodationFacilityService.findAll();
        model.addAttribute("accommodationFacilityList", accommodationFacilityList);

        return "accommodation-update";
    }

}
