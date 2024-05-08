package com.simsasookbak.global.page.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class NavbarController {

    @GetMapping("/myAccommodation/{user_id}")
    public String details(@PathVariable Integer user_id){

        return "my-accommodation-list";
    }
}
