package com.simsasookbak.global.page.controller;

import com.simsasookbak.accommodation.dto.AccommodationDto;
import com.simsasookbak.accommodation.service.AccommodationService;
import com.simsasookbak.review.dto.ScoreAverageDto;
import com.simsasookbak.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainPageController {

    private final AccommodationService accommodationService;
    private final ReviewService reviewService;

    @GetMapping("/")
    public String getHighScoreAccommodation(Model model) {
        List<AccommodationDto> highScoreAccommodations = accommodationService.getHighScoreAccommodation();
        model.addAttribute("highScoreAccommodations", highScoreAccommodations);
        return "index";
    }
}
