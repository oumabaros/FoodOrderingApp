package com.pm.restaurantservice.dto;

import java.util.List;

public class SearchRestaurantResponseDTO {
    private List<RestaurantResponseDTO> data;
    private Pagination pagination;
    public SearchRestaurantResponseDTO(List<RestaurantResponseDTO> data,Pagination pagination){
        this.data=data;
        this.pagination=pagination;
    }

    public List<RestaurantResponseDTO> getData() {
        return data;
    }

    public void setData(List<RestaurantResponseDTO> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}
