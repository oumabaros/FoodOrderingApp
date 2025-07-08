package com.pm.patientservice.controller;

import com.pm.patientservice.config.PatientProperties;
import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    private final PatientProperties patientProperties;

    public HomeController(PatientProperties patientProperties) {
        this.patientProperties = patientProperties;
    }

    @GetMapping("/")
    public String getGreeting() {
        return patientProperties.getGreeting();
    }
}
