package com.simsasookbak.review.controller;

import com.simsasookbak.review.domain.Review;
import com.simsasookbak.review.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review/register/{acom_id}")
    public String registReview(@ModelAttribute Review review, @PathVariable Long acom_id){
        log.info("{}",review.getId());
        log.info("{}",review.getContent());

        reviewService.registComment(review);
        return "redirect:/accommodation/{acom_id}";
    }

}
