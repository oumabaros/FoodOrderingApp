package com.pm.configservice.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    public HomeController(){}

    @GetMapping("/")
    public String getGreeting() {
        return "Config Working";
    }
}