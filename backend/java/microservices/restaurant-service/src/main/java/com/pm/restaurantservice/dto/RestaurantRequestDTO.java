package com.pm.restaurantservice.dto;

import com.pm.restaurantservice.model.MenuItem;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.List;


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
    private String imageUrl;
    private MultipartFile imageFile;
    @NotBlank
    private List<String> cuisines;
    @NotBlank
    private List<MenuItem> menuItems;
    @NotBlank
    private String user;
    private String auth0Id;

    public RestaurantRequestDTO(String restaurantName,
                                String city,
                                String country,
                                Double deliveryPrice,
                                Integer estimatedDeliveryTime,
                                List<MenuItem> menuItems,
                                List<String> cuisines,
                                MultipartFile imageFile){
        this.restaurantName=restaurantName;
        this.city=city;
        this.country=country;
        this.deliveryPrice=deliveryPrice;
        this.estimatedDeliveryTime=estimatedDeliveryTime;
        this.menuItems=menuItems;
        this.cuisines=cuisines;
        this.imageFile= imageFile;
    }
    public String getAuth0Id() {
        return auth0Id;
    }

    public void setAuth0Id(String auth0Id) {
        this.auth0Id = auth0Id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }
}