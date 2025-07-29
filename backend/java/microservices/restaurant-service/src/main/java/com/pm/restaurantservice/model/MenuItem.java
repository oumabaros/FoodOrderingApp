package com.pm.restaurantservice.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "menuItems")
public class MenuItem {

    @NotNull
    public String name;
    @NotNull
    public Double price;

    public MenuItem(String name,Double price){
        this.name=name;
        this.price=price;
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
