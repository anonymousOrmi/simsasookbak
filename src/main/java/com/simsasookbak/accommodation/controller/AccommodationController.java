package com.simsasookbak.accommodation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.service.AccommodationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;
    @GetMapping("/{acom_id}")
    public String details(@PathVariable Integer acom_id){

        return "details";
    }
    @GetMapping("/{acom_id}/{room_id}/reservation")
    public void reservation(@PathVariable Integer acom_id,@PathVariable Integer room_id){

    }
    @PostMapping("/reservation/message")
    public void reservationMessage(){

    }
    @PostMapping("/{acom_id}/comment")
    public void review(@PathVariable Integer acom_id){

    }

    @GetMapping("/search")
    public ResponseEntity<?> searchAccommodation(@RequestParam("keyword") String keyword) {
        List<AccommodationDto> accommodations = accommodationService.searchAccommodation(keyword);
        if (accommodations.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("검색 내용이 없습니다.");
        }
        return ResponseEntity.ok().body(accommodations);
    }
}
