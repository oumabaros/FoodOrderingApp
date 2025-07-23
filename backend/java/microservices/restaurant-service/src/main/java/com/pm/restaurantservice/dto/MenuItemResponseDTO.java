package com.pm.restaurantservice.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class MenuItemResponseDTO {
    private String name;

    public Double price;

    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

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
