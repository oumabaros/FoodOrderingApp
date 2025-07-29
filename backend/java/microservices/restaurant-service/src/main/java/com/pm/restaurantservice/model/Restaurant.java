package com.pm.restaurantservice.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.List;

@Document(collection = "restaurants")
public class Restaurant {
  @Id
  private String id;
  @NotNull
  private String user;
  @NotNull
  private String restaurantName;
  @NotNull
  private String city;
  @NotNull
  private String country;
  @NotNull
  private Double deliveryPrice;
  @NotNull
  private Integer estimatedDeliveryTime;
  @NotNull
  private String imageUrl;
  @NotNull
  private LocalDate lastUpdated;
  private List<MenuItem>  menuItems;
  @NotNull
  private List <String> cuisines;
  @NotNull
  private String auth0Id;
  public String getId() {
    return id;
  }
  public String getAuth0Id() {
    return auth0Id;
  }

  public void setAuth0Id(String auth0Id) {
    this.auth0Id = auth0Id;
  }

  public List<MenuItem> getMenuItems() {
    return menuItems;
  }

  public void setMenuItems(List<MenuItem> menuItems) {
    this.menuItems = menuItems;
  }

  public List<String> getCuisines() {
    return cuisines;
  }

  public void setCuisines(List<String> cuisines) {
    this.cuisines = cuisines;
  }

  public String getRestaurantName() {
    return restaurantName;
  }

  public void setRestaurantName(String restaurantName) {
    this.restaurantName = restaurantName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public Double getDeliveryPrice() {
    return deliveryPrice;
  }

  public void setDeliveryPrice(Double deliveryPrice) {
    this.deliveryPrice = deliveryPrice;
  }

  public Integer getEstimatedDeliveryTime() {
    return estimatedDeliveryTime;
  }

  public void setEstimatedDeliveryTime(Integer estimatedDeliveryTime) {
    this.estimatedDeliveryTime = estimatedDeliveryTime;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public LocalDate getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(LocalDate lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }
}