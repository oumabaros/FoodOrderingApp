package com.pm.restaurantservice.dto;

import jakarta.validation.constraints.NotNull;

public class MenuItemRequestDTO {
    @NotNull
    public String name;
    @NotNull
    public Double price;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
