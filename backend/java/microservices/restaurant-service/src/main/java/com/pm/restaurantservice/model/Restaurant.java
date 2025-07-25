package com.pm.restaurantservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "restaurants")
public class Restaurant {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
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
  @NotNull
  @OneToMany(mappedBy="restaurant")
  private Set<MenuItem> menuItems;

  @NotNull
  private String userId;

  @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
  @CollectionTable(name = "cuisines", joinColumns = @JoinColumn(name = "restaurant_id"))
  @Column(name = "cuisines", nullable = false)
  private List<String> cuisines = new ArrayList<>();

  public List<String> getCuisines() {
    return cuisines;
  }

  public void setCuisines(List<String> cuisines) {
    this.cuisines = cuisines;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Set<MenuItem> getMenuItems() {
    return menuItems;
  }

  public void setMenuItems(Set<MenuItem> menuItems) {
    this.menuItems = menuItems;
  }

  public UUID getId() {
    return id;
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


}
