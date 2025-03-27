package com.ahmed.media_sense_api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {
    @GetMapping("/test-auth")
    public String test() {
        return "Access Authorized !";
    }
}
