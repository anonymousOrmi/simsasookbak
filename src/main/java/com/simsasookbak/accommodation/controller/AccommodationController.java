package com.simsasookbak.accommodation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("accommodation")
public class AccommodationController {


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
}
