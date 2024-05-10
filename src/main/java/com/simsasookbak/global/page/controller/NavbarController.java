package com.simsasookbak.global.page.controller;

import com.simsasookbak.accommodation.dto.response.AccommodationResponse;
import com.simsasookbak.accommodation.service.AccommodationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class NavbarController {

    private final AccommodationService accommodationService;

    @GetMapping("/myAccommodations/{member_id}")
    public String showMyAccommodations(@PathVariable(name = "member_id") Long memberId, Model model){

        List<AccommodationResponse> myAccommodationList = accommodationService.findMyAccommodations(memberId);
        model.addAttribute("myAccommodations", myAccommodationList);

        return "my-accommodation-list";
    }
}
