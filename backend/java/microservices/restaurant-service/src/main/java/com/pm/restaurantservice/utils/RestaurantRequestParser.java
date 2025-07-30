package com.pm.restaurantservice.utils;

import com.pm.restaurantservice.dto.RestaurantRequestDTO;
import com.pm.restaurantservice.model.MenuItem;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestaurantRequestParser {

    public static RestaurantRequestDTO parseRestaurantRequest(HttpServletRequest request) {
        System.out.println("IN CREATE");
        if (!(request instanceof MultipartHttpServletRequest)) {
            System.out.println("REQUEST NULL");
            return null;
        }
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MenuItem> menuItems = new ArrayList<>();
        List<String> cuisines = new ArrayList<>();
        Optional<MultipartFile> imageFile = null;
        System.out.println("IMAGE VAR DECLARED");
        String restaurantName = multipartRequest.getParameter("restaurantName");
        String city = multipartRequest.getParameter("city");
        String country = multipartRequest.getParameter("country");
        Double deliveryPrice = Double.parseDouble(multipartRequest.getParameter("deliveryPrice"));
        Integer estimatedDeliveryTime = Integer.parseInt(request.getParameter("estimatedDeliveryTime"));

        System.out.println("START CHECK IMAGE FILE");
        if (request.getParameter("imageFile")!=null) {
            imageFile = Optional.of(multipartRequest.getFile("imageFile"));
            System.out.println("IMAGE VERIFIED");
        }
        System.out.println("ADD MENU ITEMS START");
        int index = 0;
        while (multipartRequest.getParameter("menuItems[" + index + "][name]") != null
                && multipartRequest.getParameter("menuItems[" + index + "][price]") != null) {
            menuItems.add(new MenuItem(
                    multipartRequest.getParameter("menuItems[" + index + "][name]"),
                    Double.parseDouble(multipartRequest.getParameter("menuItems[" + index + "][price]"))
            ));
            index++;
        }
        System.out.println("MENU ITEMS ADDED");
        System.out.println("ADD CUISINES START");
        int c = 0;
        while (multipartRequest.getParameter("cuisines[" + c + "]") != null) {
            cuisines.add(multipartRequest.getParameter("cuisines[" + c + "]"));
            c++;
        }
        System.out.println("CUISINES ADDED");
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
        System.out.println("IN UPDATE");
        if (!(request instanceof MultipartHttpServletRequest)) {
            System.out.println("IN UPDATE AND NULL");
            return null;
        }
        System.out.println("IN UPDATE AND FINE");
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MenuItem> menuItems = new ArrayList<>();
        List<String> cuisines = new ArrayList<>();
        Optional<MultipartFile> imageFile = null;
        String restaurantName = multipartRequest.getParameter("restaurantName");
        String city = multipartRequest.getParameter("city");
        String country = multipartRequest.getParameter("country");
        Double deliveryPrice = Double.parseDouble(multipartRequest.getParameter("deliveryPrice"));
        Integer estimatedDeliveryTime = Integer.parseInt(request.getParameter("estimatedDeliveryTime"));
        System.out.println("MID UPDATE");
        System.out.println("FILE STATUS? "+request.getParameter("imageFile"));

        if (request.getParameter("imageFile")!=null) {
            System.out.println("PROCESSING FILE");
            imageFile = Optional.of(multipartRequest.getFile("imageFile"));
        }
        System.out.println("PROCESSED FILE");
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
        System.out.println("RETURNING");
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
