package com.pm.restaurantservice.model;

import jakarta.validation.constraints.NotNull;

public class MenuItem {
    @NotNull
    public String name;
    @NotNull
    public Double price;
}
