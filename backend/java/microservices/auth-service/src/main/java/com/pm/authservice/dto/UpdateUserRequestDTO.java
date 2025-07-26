package com.pm.authservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUserRequestDTO {
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;
    @NotBlank(message = "Address Line1 is required")
    private String addressLine1;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "Country is required")
    private String country;

    public @Size(max = 100, message = "Name cannot exceed 100 characters") String getName() {
        return name;
    }

    public void setName(
            @Size(max = 100, message = "Name cannot exceed 100 characters") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(
            @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }
    public @NotBlank(message = "Address is required") String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(@NotBlank(message = "Address is required") String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public @NotBlank(message = "City is required") String getCity() {
        return city;
    }

    public void setCity(@NotBlank(message = "City is required") String city) {
        this.city = city;
    }

    public @NotBlank(message = "Country is required") String getCountry() {
        return country;
    }

    public void setCountry(@NotBlank(message = "Country is required") String country) {
        this.country = country;
    }
}
