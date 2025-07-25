package com.pm.restaurantservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RestaurantRequestDTO {

  @NotBlank(message = "Restaurant Name is required")
  @Size(max = 100, message = "Name cannot exceed 100 characters")
  private String restaurantName;
  @NotBlank(message = "City is required")
  @Size(max = 100, message = "City cannot exceed 100 characters")
  private String city;
  @NotBlank(message = "Country is required")
  @Size(max = 100, message = "Country cannot exceed 100 characters")
  private String country;
  @NotBlank(message = "Delivery Price is required")
  private Double deliveryPrice;
  @NotBlank(message = "Estimated Delivery Time is required")
  private Integer estimatedDeliveryTime;
  private LocalDate lastUpdated;
  private List<String> cuisines = new ArrayList<>();
  private String imageUrl;
  @NotBlank(message = "Image is required")
  private File imageFile;
  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public File getImageFile() {
    return imageFile;
  }

  public void setImageFile(File imageFile) {
    this.imageFile = imageFile;
  }


  public String getRestaurantName() {
    return restaurantName;
  }

  public void setRestaurantName(@NotBlank(message = "Restaurant Name is required") String restaurantName) {
    this.restaurantName = restaurantName;
  }

  public String getCity() {
    return city;
  }

  public void setCity(@NotBlank(message = "City is required") String city) {
    this.city = city;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(@NotBlank(message = "Country is required") String country) {
    this.country = country;
  }

  public Double getDeliveryPrice() {
    return deliveryPrice;
  }

  public void setDeliveryPrice(@NotBlank(message = "Delivery Price is required") Double deliveryPrice) {
    this.deliveryPrice = deliveryPrice;
  }

  public Integer getEstimatedDeliveryTime() {
    return estimatedDeliveryTime;
  }

  public void setEstimatedDeliveryTime(@NotBlank(message = "Estimated Delivery Time is required") Integer estimatedDeliveryTime) {
    this.estimatedDeliveryTime = estimatedDeliveryTime;
  }

  public LocalDate getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(LocalDate lastUpdated) {
    this.lastUpdated = lastUpdated;
  }



  public List<String> getCuisines() {
    return cuisines;
  }

  public void setCuisines(List<String> cuisines) {
    this.cuisines = cuisines;
  }
}
