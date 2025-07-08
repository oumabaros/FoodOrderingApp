package com.pm.patientservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@ConfigurationPropertiesScan
public class PatientServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(PatientServiceApplication.class, args);
  }

}
