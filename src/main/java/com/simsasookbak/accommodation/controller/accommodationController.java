package com.simsasookbak.accommodation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class accommodationController {
    @GetMapping("/accommodation/{acom_id}")
    public String details(@PathVariable Integer acom_id){

        return "details";
    }
    @GetMapping("/accommodation/{acom_id}/{room_id}/reservation")
    public void reservation(@PathVariable Integer acom_id,@PathVariable Integer room_id){

    }
    @PostMapping("/reservation/message")
    public void reservationMessage(){

    }
    @PostMapping("/accommodation/{acom_id}/comment")
    public void review(@PathVariable Integer acom_id){

    }
}
