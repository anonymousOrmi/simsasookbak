package com.simsasookbak.accommodation.controller;

import com.simsasookbak.accommodation.domain.Accommodation;
import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.room.domain.Room;
import com.simsasookbak.room.service.RoomService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;
    private final RoomService roomService;

    @GetMapping("/{acom_id}")
    public String details(@PathVariable Integer acom_id){

        return "details";
    }

//    @GetMapping("/{acom_id}/{room_id}/reservationPage")
//    public String viewReservationPage(@PathVariable Long acom_id, @PathVariable Long room_id, Model model) {
//
//        Accommodation accommodation = accommodationService.getAccommodationById(acom_id);
//        Room room = roomService.getRoomById(room_id);
//        model.addAttribute("accommodation", accommodation);
//        model.addAttribute("room", room);
//
//        return "reservationPage";
//    }

    @PostMapping("/reservation/message")
    public void reservationMessage(){
    }
    @PostMapping("/{acom_id}/comment")
    public void review(@PathVariable Integer acom_id){
    }
}
