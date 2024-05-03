package com.simsasookbak.reservation.controller;

import com.simsasookbak.reservation.dto.request.PopularRegionRequest;
import com.simsasookbak.reservation.dto.response.ReservationResponse;
import com.simsasookbak.reservation.service.ReservationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/reservation")
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/popular-region")
    public ResponseEntity<List<ReservationResponse>> getPopularRegionsByDate(@ModelAttribute PopularRegionRequest request) {
        List<ReservationResponse> response = reservationService.findPopularRegionsByDate(request);
        return ResponseEntity.ok(response);
    }
}
