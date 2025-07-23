package com.pm.restaurantservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class MenuItemRequestDTO {
    @NotBlank(message = "Name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name is required") String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(@NotBlank(message = "Price is required") Double price) {
        this.price = price;
    }

    @NotBlank(message = "Price is required")
    public Double price;
}
