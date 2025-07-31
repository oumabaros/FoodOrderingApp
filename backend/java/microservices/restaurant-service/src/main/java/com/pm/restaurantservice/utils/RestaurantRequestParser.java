package com.pm.restaurantservice.utils;

import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.model.MenuItem;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class RestaurantRequestParser {

    public static RestaurantRequestDTO parseRestaurantRequest(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MenuItem> menuItems = new ArrayList<>();
        List<String> cuisines = new ArrayList<>();

        String restaurantName = multipartRequest.getParameter("restaurantName");
        String city = multipartRequest.getParameter("city");
        String country = multipartRequest.getParameter("country");
        Double deliveryPrice = Double.parseDouble(multipartRequest.getParameter("deliveryPrice"));
        Integer estimatedDeliveryTime = Integer.parseInt(request.getParameter("estimatedDeliveryTime"));

        MultipartFile imageFile = multipartRequest.getFile("imageFile");

        int index = 0;
        while (multipartRequest.getParameter("menuItems[" + index + "][name]") != null
                && multipartRequest.getParameter("menuItems[" + index + "][price]") != null) {
            menuItems.add(new MenuItem(
                    multipartRequest.getParameter("menuItems[" + index + "][name]"),
                    Double.parseDouble(multipartRequest.getParameter("menuItems[" + index + "][price]"))
            ));
            index++;
        }

        int c = 0;
        while (multipartRequest.getParameter("cuisines[" + c + "]") != null) {
            cuisines.add(multipartRequest.getParameter("cuisines[" + c + "]"));
            c++;
        }
        return new RestaurantRequestDTO(
                restaurantName,
                city,
                country,
                deliveryPrice,
                estimatedDeliveryTime,
                menuItems,
                cuisines,
                imageFile
        );
    }

    public static RestaurantRequestDTO parseRestaurantRequestUpdate(HttpServletRequest request) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MenuItem> menuItems = new ArrayList<>();
        List<String> cuisines = new ArrayList<>();
        MultipartFile imageFile = multipartRequest.getFile("imageFile");

        String restaurantName = multipartRequest.getParameter("restaurantName");
        String city = multipartRequest.getParameter("city");
        String country = multipartRequest.getParameter("country");
        Double deliveryPrice = Double.parseDouble(multipartRequest.getParameter("deliveryPrice"));
        Integer estimatedDeliveryTime = Integer.parseInt(request.getParameter("estimatedDeliveryTime"));

        int index = 0;
        while (multipartRequest.getParameter("menuItems[" + index + "][name]") != null
                && multipartRequest.getParameter("menuItems[" + index + "][price]") != null) {
            menuItems.add(new MenuItem(
                    multipartRequest.getParameter("menuItems[" + index + "][name]"),
                    Double.parseDouble(multipartRequest.getParameter("menuItems[" + index + "][price]"))
            ));
            index++;
        }
        int c = 0;
        while (multipartRequest.getParameter("cuisines[" + c + "]") != null) {
            cuisines.add(multipartRequest.getParameter("cuisines[" + c + "]"));
            c++;
        }
        return new RestaurantRequestDTO(
                restaurantName,
                city,
                country,
                deliveryPrice,
                estimatedDeliveryTime,
                menuItems,
                cuisines,
                imageFile
        );
    }
}
